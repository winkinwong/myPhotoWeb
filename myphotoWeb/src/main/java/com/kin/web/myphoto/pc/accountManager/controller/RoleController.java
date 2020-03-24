package com.kin.web.myphoto.pc.accountManager.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kin.web.myphoto.global.ResultBean;
import com.kin.web.myphoto.pc.accountManager.entity.Function;
import com.kin.web.myphoto.pc.accountManager.entity.Role;
import com.kin.web.myphoto.pc.accountManager.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.kin.web.myphoto.base.BaseController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
@RestController
@RequestMapping("/accountManager/role")
public class RoleController extends BaseController {
    private static final Logger logger=LoggerFactory.getLogger(RoleController.class);
    @Resource
    private IRoleService roleService;

    //1.添加角色
    @PostMapping("/addRole")
    public ResultBean addRole(Role role){
        logger.info("添加角色 role:",role);
        roleService.addRole(role);
        return ResultBean.success("添加角色成功");
    }

    //2.删除角色
    @GetMapping("/delRole/{id}")
    public ResultBean delRole(@PathVariable Long id){
        logger.info("删除角色 roleId:",id);
        roleService.delRole(id);
        return ResultBean.success("删除成功");
    }

    //3.展示所有角色
    @GetMapping("/showAllRole")
    public ResultBean showAllRole(Long currentPage, Long rows){
        if (currentPage==null&&rows==null){
            currentPage=1L;
            rows=10L;
        }
        ResultBean<Page<Role>> resultBean = new ResultBean<>();
        //分页查询角色
        resultBean.setData(roleService.listRoleByPage(currentPage,rows));
        return resultBean;
    }
}
