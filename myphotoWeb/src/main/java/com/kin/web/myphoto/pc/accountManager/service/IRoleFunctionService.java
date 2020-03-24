package com.kin.web.myphoto.pc.accountManager.service;

import com.kin.web.myphoto.pc.accountManager.entity.RoleFunction;
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
public interface IRoleFunctionService extends IService<RoleFunction> {
    List<RoleFunction> listRFByFunctionId(Long functionId);
    List<Long> listFunctionIds(Long roleId);
    int addRoleFunction(Long roleId,Long functionId);
    int delRoleFunction(Long functionId);
}
