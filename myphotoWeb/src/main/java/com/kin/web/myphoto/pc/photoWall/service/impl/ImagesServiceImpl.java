package com.kin.web.myphoto.pc.photoWall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kin.web.myphoto.exception.Error;
import com.kin.web.myphoto.exception.ImagesException;
import com.kin.web.myphoto.global.ResultBean;
import com.kin.web.myphoto.pc.accountManager.entity.User;
import com.kin.web.myphoto.pc.photoWall.entity.Images;
import com.kin.web.myphoto.pc.photoWall.mapper.ImagesMapper;
import com.kin.web.myphoto.pc.photoWall.req.UploadImgReq;
import com.kin.web.myphoto.pc.photoWall.service.IImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements IImagesService {

    @Value("${images-upload-path}")
    private String uploadPath;

    @Resource
    private ImagesMapper imagesMapper;

    @Override
    public ResultBean uploadImgHandle(UploadImgReq req) {
        File uploadDirectory = new File(uploadPath);
        if (uploadDirectory.exists()){
            if(!uploadDirectory.isDirectory()){
                uploadDirectory.delete();
            }
        }else {
            uploadDirectory.mkdir();
        }
        //这里可以支持多文件上传
        if (req.getFiles() != null && req.getFiles().length >= 1) {
            BufferedOutputStream bw = null;
            List<Images> images=new ArrayList<>();
            try {
                for (MultipartFile file : req.getFiles()) {
                    String fileName = file.getOriginalFilename();
                    //判断是否有文件且是否为图片文件
                    if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && isImageFile(fileName)) {
                        //创建输出文件对象
                        String imageName =  UUID.randomUUID().toString();
                        String filePath=uploadPath + "/" +imageName+ getFileType(fileName);
                        File outFile = new File(filePath);
                        //拷贝文件到输出文件对象
                        FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
                        //将图片信息存入数据表
                        Images image=new Images();
                        image.setUploaderIp(req.getUploaderIp());
                        image.setUploaderId(req.getUserId());
                        image.setUploaderName(req.getUserName());
                        image.setDeleted(0);
                        image.setImageName(imageName);
                        image.setImageRealurl(filePath);
                        image.setImageUrl("/img/upload-img/"+imageName+getFileType(fileName));
                        image.setImageType(getFileType(fileName));
                        image.setIsDisplay(req.getIsDisplay());
                        image.setIsPublic(req.getIsPublic());
                        image.setImageCategory(req.getImageCategory());
                       int flag =  imagesMapper.insert(image);
                       if (flag==0){
                           throw new ImagesException(Error.IMG_SAVE_FILE);
                       }
                    }else {
                        throw new ImagesException(Error.IMG_UPLOAD_FAIL);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResultBean.success("上传成功");
    }

    @Override
    public Page<Images> listImgByPage(Long currentPage, Long rows) {
        IPage<Images>page = new Page<>(currentPage,rows);
        return (Page<Images>) imagesMapper.selectPage(page,null);
    }

    @Override
    public Page<Images> getPersonalImgByPage(Long currentPage, Long rows, User user) {
        IPage<Images> page = new Page<>(currentPage,rows);
        return (Page<Images>) imagesMapper.selectPage(page,new QueryWrapper<Images>().eq("uploader_id",user.getUserId()));
    }

    @Override
    public int delImg(User user, Long id) {
        Images images =imagesMapper.selectById(id);
        if (images==null){
            throw new ImagesException(Error.IMG_NOT_FOUND);
        }
        //先检查登录用户是否为超级管理员，若是则允许删除任何图片
        if (!(user.getUserName().equals("admin"))){
            //1.根据id查询图片是否为当前用户上传图片，若不是需要返回信息
            if (images.getUploaderId()!=user.getUserId()){
                throw new ImagesException(Error.IMG_NOT_BELONG);
            }
            //删除本地文件
            File file = new File(images.getImageRealurl());
            if (file.exists()){
                file.delete();
            }
            return imagesMapper.deleteById(id);
        }
        //删除本地文件
        File file = new File(images.getImageRealurl());
        if (file.exists()){
            file.delete();
        }
        return imagesMapper.deleteById(id);
    }


    private Boolean isImageFile(String fileName) {
        String[] img_type = new String[]{".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        if (fileName == null) {
            return false;
        }
        fileName = fileName.toLowerCase();
        for (String type : img_type) {
            if (fileName.endsWith(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件后缀名
     *
     * @param fileName
     * @return
     */
    private String getFileType(String fileName) {
        if (fileName != null && fileName.indexOf(".") >= 0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }
}
