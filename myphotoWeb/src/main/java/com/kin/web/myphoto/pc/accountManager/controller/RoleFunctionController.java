package com.kin.web.myphoto.pc.accountManager.controller;


import com.kin.web.myphoto.global.ResultBean;
import com.kin.web.myphoto.pc.accountManager.entity.Function;
import com.kin.web.myphoto.pc.accountManager.entity.RoleFunction;
import com.kin.web.myphoto.pc.accountManager.service.IFunctionService;
import com.kin.web.myphoto.pc.accountManager.service.IRoleFunctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/accountManager/roleFunction")
public class RoleFunctionController extends BaseController {
    private static final Logger logger=LoggerFactory.getLogger(RoleFunctionController.class);
    @Resource
    private IRoleFunctionService roleFunctionService;
    @Resource
    private IFunctionService functionService;

    //1.展示当前角色所有权限 2.让用户选择权限，为角色添加权限 3.删除当前角色权限

    //1.展示当前角色的所有权限
    @GetMapping("/showRoleFunctions/{id}")
    public ResultBean showRoleFunctions(@PathVariable Long id){
        List<Long> functionIds = roleFunctionService.listFunctionIds(id);
        List<Function> functions = functionService.listFunctionByFunctionIds(functionIds);
        return ResultBean.success(functions);
    }
    //2.为角色添加权限，只允许一个一个添加，添加完一个后，选择列表刷新，显示未添加的权限
    @GetMapping("/addRoleFunction/{id}")
    public ResultBean addRoleFunction(@PathVariable Long id, Long functionId){
        logger.info("为角色添加权限 roleId:",id,"functionId:",functionId);
        roleFunctionService.addRoleFunction(id,functionId);
        return ResultBean.success("添加成功");
    }
    //3.删除当前角色权限
    @GetMapping("/delRoleFunction/{id}")
    public ResultBean delRoleFunction(@PathVariable Long id){
        logger.info("删除角色权限 functionId:",id);
        roleFunctionService.delRoleFunction(id);
        return ResultBean.success("删除成功");
    }
}
