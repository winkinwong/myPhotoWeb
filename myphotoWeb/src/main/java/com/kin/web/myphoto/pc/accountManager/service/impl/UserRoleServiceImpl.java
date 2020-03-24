package com.kin.web.myphoto.pc.accountManager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kin.web.myphoto.exception.Error;
import com.kin.web.myphoto.exception.RoleException;
import com.kin.web.myphoto.pc.accountManager.entity.Role;
import com.kin.web.myphoto.pc.accountManager.entity.UserRole;
import com.kin.web.myphoto.pc.accountManager.mapper.RoleMapper;
import com.kin.web.myphoto.pc.accountManager.mapper.UserRoleMapper;
import com.kin.web.myphoto.pc.accountManager.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Override
    public int addUserRole(Long userId, Long roleId) {
        //添加之前先查询有没该角色，且查询该用户有没添加过角色，如果有将以前的角色先删除。
        Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("role_id",roleId));
        if (role==null){
            throw new RoleException(Error.ROLE_NOT_FOUND);
        }
        UserRole checkUserRole = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("user_id",userId));
        if (checkUserRole!=null){
            userRoleMapper.delete(new QueryWrapper<UserRole>().eq("user_id",userId));
        }
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        return userRoleMapper.insert(userRole);
    }
}
