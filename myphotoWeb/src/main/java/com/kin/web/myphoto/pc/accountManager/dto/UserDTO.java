package com.kin.web.myphoto.pc.accountManager.dto;

import com.kin.web.myphoto.base.BaseEntity;
import com.kin.web.myphoto.pc.accountManager.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO extends BaseEntity {
    private String userPassword;
    private String userName;
    private String userRealName;
    private String address;
    private String phoneNumber;
    private LocalDateTime birth;
    private String sex;
    private String headImage;
    private Integer enable;
    private Integer deleted;

    public User toConvertUserEntity(){
        User user = new User();
        user.setAddress(this.address);
        user.setBirth(this.birth);
        user.setDeleted(this.deleted);
        user.setEnable(this.enable);
        user.setHeadImage(this.headImage);
        user.setPhoneNumber(this.phoneNumber);
        user.setUserName(this.userName);
        user.setUserPassword(this.userPassword);
        user.setUserRealName(this.userRealName);
        user.setSex(this.sex);
        return user;
    }
}
