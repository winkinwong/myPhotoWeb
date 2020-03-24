package com.kin.web.myphoto.pc.photoWall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.kin.web.myphoto.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Images extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 图片id
     */
    @TableId(value = "image_id", type = IdType.AUTO)
    private Long imageId;

    /**
     * 图片名称
     */
    private String imageName;

    /**
     * 图片类型例如 .jpg/.png
     */
    private String imageType;

    /**
     * 图片真实地址
     */
    private String imageRealurl;

    /**
     * 图片url
     */
    private String imageUrl;


    /**
     * 图片上传者名称
     */
    private String uploaderName;

    /**
     * 上传者id
     */
    private Long uploaderId;

    /**
     * 上传者ip
     */
    private String uploaderIp;

    /**
     * 图片所属分类
     */
    private String imageCategory;

    /**
     * 是否显示 1：显示 0：不显示
     */
    private Integer isDisplay;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 删除标记 1：删除 0：未删除
     */
    private Integer deleted;

    /**
     * 是否公开 1：公开 0：私有
     */
    private Integer isPublic;


}
