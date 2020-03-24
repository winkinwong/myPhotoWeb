package com.kin.web.myphoto.pc.accountManager.service;

import com.kin.web.myphoto.pc.accountManager.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
public interface IUserRoleService extends IService<UserRole> {
    int addUserRole(Long userId,Long roleId);
}
