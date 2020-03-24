package com.kin.web.myphoto.pc.accountManager.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kin.web.myphoto.global.ResultBean;
import com.kin.web.myphoto.pc.accountManager.entity.Function;
import com.kin.web.myphoto.pc.accountManager.service.IFunctionService;
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
@RequestMapping("/accountManager/function")
public class FunctionController extends BaseController {
    private static final Logger logger=LoggerFactory.getLogger(FunctionController.class);
    @Resource
    private IFunctionService functionService;
    //TODO  权限功能 1.添加权限 2.删除权限  3.列出所有权限

    //1.添加权限
    @PostMapping("/addFunction")
    public ResultBean addFunction(Function function){
        logger.info("添加权限 function:",function);
        functionService.addFunction(function);
        return ResultBean.success("添加成功");
    }
    //2.删除权限
    @GetMapping("/delFunction/{id}")
    public ResultBean delFunction(@PathVariable Long id){
        logger.info("删除权限 functionId:",id);
        functionService.delFunction(id);
        return ResultBean.success("删除成功");
    }

    //3.列出所有权限(根据角色筛选，若角色已添加该权限，则不显示)
    @GetMapping("/showAllFunction/{roleId}")
    public ResultBean showAllFunction(@PathVariable Long roleId){
        ResultBean resultBean = new ResultBean<>();
        //分页查询权限
        resultBean.setData(functionService.listFunctionByPage(roleId));
        return resultBean;
    }

}
