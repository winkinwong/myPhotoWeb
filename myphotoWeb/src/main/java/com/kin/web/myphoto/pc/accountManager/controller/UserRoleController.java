package com.kin.web.myphoto.pc.accountManager.controller;



import com.kin.web.myphoto.global.ResultBean;

import com.kin.web.myphoto.pc.accountManager.entity.User;
import com.kin.web.myphoto.pc.accountManager.service.IRoleService;
import com.kin.web.myphoto.pc.accountManager.service.IUserRoleService;
import com.kin.web.myphoto.pc.accountManager.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.kin.web.myphoto.base.BaseController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
@RestController
@RequestMapping("/accountManager/userRole")
public class UserRoleController extends BaseController {
    private static final Logger logger=LoggerFactory.getLogger(UserRoleController.class);
    @Resource
    private IUserService userService;
    @Resource
    private IUserRoleService userRoleService;

    //0.只允许超级管理员调用以下接口
    //1.列出所有用户
    @GetMapping("/showAllUser")
    public ResultBean showAllUser(){
        List<User> users = userService.listAllUser();
        return ResultBean.success(users);
    }
    //2.列出所有角色(调用roleController接口)
    //3.为指定用户添加一个角色，只允许添加一个角色
    @GetMapping("/addUserRole/{userId}")
    public ResultBean addUserRole(@PathVariable Long userId,Long roleId){
        logger.info("用户添加角色 用户Id",userId,"角色Id",roleId);
        userRoleService.addUserRole(userId,roleId);
        return ResultBean.success("添加成功");
    }

}
