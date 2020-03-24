package com.kin.web.myphoto.pc.accountManager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kin.web.myphoto.pc.accountManager.entity.Function;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
public interface IFunctionService extends IService<Function> {
    List<Function>listFunctionByPage(Long roleId);
    int addFunction(Function function);
    int delFunction(Long id);
    List<Function> listFunctionByFunctionIds(List<Long> functionIds);
}
