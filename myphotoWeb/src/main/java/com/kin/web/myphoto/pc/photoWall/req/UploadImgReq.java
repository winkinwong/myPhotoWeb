package com.kin.web.myphoto.pc.photoWall.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadImgReq {
    private Long userId;
    private String userName;
    private String uploaderIp;
    private MultipartFile files[];
    /**
     * 是否公开 1：公开 0：私有
     */
    private Integer isPublic;
    /**
     * 是否显示 1：显示 0：不显示
     */
    private Integer isDisplay;
    /**
     * 图片所属分类
     */
    private String imageCategory;
}
