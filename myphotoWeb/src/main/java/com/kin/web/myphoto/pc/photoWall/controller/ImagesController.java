package com.kin.web.myphoto.pc.photoWall.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kin.web.myphoto.exception.Error;
import com.kin.web.myphoto.exception.UserException;
import com.kin.web.myphoto.global.ResultBean;


import com.kin.web.myphoto.global.ThreadLocalHelper;
import com.kin.web.myphoto.pc.accountManager.entity.User;
import com.kin.web.myphoto.pc.photoWall.entity.Images;
import com.kin.web.myphoto.pc.photoWall.req.UploadImgReq;
import com.kin.web.myphoto.pc.photoWall.service.IImagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.kin.web.myphoto.base.BaseController;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
@RestController
@RequestMapping("/photoWall/images")
public class ImagesController extends BaseController {
    private static final Logger logger= LoggerFactory.getLogger(ImagesController.class);

    @Resource
    private IImagesService imagesService;
    //1.上传图片
    @PostMapping("/uploadImg")
    public ResultBean uploadImg(UploadImgReq req, HttpServletRequest request){
        logger.info("图片上传,files[]=",req.getFiles());
        User user =ThreadLocalHelper.getSessionUser();
        if (user==null){
            throw new UserException(Error.USER_LOGOUT);
        }
        req.setUserId(user.getUserId());
        req.setUserName(user.getUserName());
        req.setUploaderIp(request.getRemoteAddr());
        return imagesService.uploadImgHandle(req);
    }
    //2.分页展示所有图片
    @GetMapping("/showImg")
    public ResultBean showAllImg(Long currentPage, Long rows){
        if (currentPage==null&&rows==null){
            currentPage=1L;
            rows=10L;
        }
        ResultBean<Page<Images>> result=new ResultBean<>();
        result.setData(imagesService.listImgByPage(currentPage,rows));
        return result;
    }
    //3.展示个人上传图片（既个人照片墙）
    @GetMapping("/showPersonalImg")
    public ResultBean showUserImg(Long currentPage, Long rows){
        if (currentPage==null&&rows==null){
            currentPage=1L;
            rows=10L;
        }
        User user = ThreadLocalHelper.getSessionUser();
        if (user==null){
            throw new UserException(Error.USER_LOGOUT);
        }
        ResultBean<Page<Images>> result=new ResultBean<>();
        result.setData(imagesService.getPersonalImgByPage(currentPage,rows,user));
        return result;
    }
    //4.删除图片
    @GetMapping("/delImg/{id}")
    public ResultBean delImg(@PathVariable Long id){
        User user = ThreadLocalHelper.getSessionUser();
        if (user==null){
            throw new UserException(Error.USER_LOGOUT);
        }
        ResultBean result =new ResultBean();
        result.setCode(imagesService.delImg(user,id));
        if (result.getCode()==0){
            result.setMessage("删除失败");
        }else {
            result.setMessage("删除成功");
        }
        return result;
    }
}
