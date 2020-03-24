package com.kin.web.myphoto.pc.accountManager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.kin.web.myphoto.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;

import com.kin.web.myphoto.pc.accountManager.dto.UserDTO;
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
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户昵称（可用于登录）
     */
    private String userName;

    /**
     * 用户真实姓名
     */
    private String userRealName;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 生日
     */
    private LocalDateTime birth;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String headImage;

    /**
     * 1：启用 0：禁用
     */
    private Integer enable;

    /**
     * 是否注销 0：否 1：是
     */
    private Integer deleted;


    public UserDTO toConvertUserDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setAddress(this.address);
        userDTO.setBirth(this.birth);
        userDTO.setDeleted(this.deleted);
        userDTO.setEnable(this.enable);
        userDTO.setHeadImage(this.headImage);
        userDTO.setPhoneNumber(this.phoneNumber);
        userDTO.setUserName(this.userName);
        userDTO.setUserPassword(this.userPassword);
        userDTO.setUserRealName(this.userRealName);
        userDTO.setSex(this.sex);
        return userDTO;
    }

}
