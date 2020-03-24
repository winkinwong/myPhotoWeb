package com.kin.web.myphoto.pc.accountManager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kin.web.myphoto.pc.accountManager.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
public interface IRoleService extends IService<Role> {

    int addRole(Role role);
    int delRole(Long roleId);
    Page<Role> listRoleByPage(Long currentPage, Long rows);

}
