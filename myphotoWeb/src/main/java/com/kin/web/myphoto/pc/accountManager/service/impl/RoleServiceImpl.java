package com.kin.web.myphoto.pc.accountManager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kin.web.myphoto.exception.Error;
import com.kin.web.myphoto.exception.RoleException;
import com.kin.web.myphoto.pc.accountManager.entity.Role;
import com.kin.web.myphoto.pc.accountManager.entity.RoleFunction;
import com.kin.web.myphoto.pc.accountManager.entity.UserRole;
import com.kin.web.myphoto.pc.accountManager.mapper.RoleFunctionMapper;
import com.kin.web.myphoto.pc.accountManager.mapper.RoleMapper;
import com.kin.web.myphoto.pc.accountManager.mapper.UserRoleMapper;
import com.kin.web.myphoto.pc.accountManager.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleFunctionMapper roleFunctionMapper;
    @Override
    public int addRole(Role role) {
        //先判断有没有存在的角色名
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name",role.getRoleName());
        Role checkRole = roleMapper.selectOne(queryWrapper);
        if (checkRole!=null){
            throw new RoleException(Error.ROLE_EXISTS);
        }
        return  roleMapper.insert(role);
    }

    @Override
    public int delRole(Long roleId) {
        //先查询该角色是否存在
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        Role checkRole = roleMapper.selectOne(queryWrapper);
        if(checkRole==null){
            throw new RoleException(Error.ROLE_NOT_FOUND);
        }
        //删除角色前，将中间表记录全删除
        //1.删除用户角色表
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("role_id",roleId);
        userRoleMapper.delete(userRoleQueryWrapper);
        //2.删除角色权限表
        QueryWrapper<RoleFunction>roleFunctionQueryWrapper = new QueryWrapper<>();
        roleFunctionQueryWrapper.eq("role_id",roleId);
        roleFunctionMapper.delete(roleFunctionQueryWrapper);
        //3.删除角色名
        return roleMapper.delete(queryWrapper);
    }

    @Override
    public Page<Role> listRoleByPage(Long currentPage, Long rows) {
        IPage<Role> page = new Page<>(currentPage,rows);
        return (Page<Role>) roleMapper.selectPage(page,null);
    }
}
