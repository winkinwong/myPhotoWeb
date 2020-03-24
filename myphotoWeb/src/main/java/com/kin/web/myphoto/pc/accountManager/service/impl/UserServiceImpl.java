package com.kin.web.myphoto.pc.accountManager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kin.web.myphoto.exception.Error;
import com.kin.web.myphoto.exception.UserException;
import com.kin.web.myphoto.pc.accountManager.dto.UserDTO;
import com.kin.web.myphoto.pc.accountManager.entity.User;
import com.kin.web.myphoto.pc.accountManager.mapper.UserMapper;
import com.kin.web.myphoto.pc.accountManager.req.UserReq;
import com.kin.web.myphoto.pc.accountManager.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public int registerAccount(UserDTO userDTO) throws Exception {
        //1 先查询是否有已存在用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userDTO.getUserName());
        User user  = userMapper.selectOne(queryWrapper);
        if (user!=null){
            throw new UserException(Error.USER_EXISTS);
        }
        //密码加密
        String EncodePsd = DigestUtils.sha512Hex(userDTO.getUserPassword());
        userDTO.setUserPassword(EncodePsd);
        userDTO.setEnable(1);
        userDTO.setDeleted(0);
        int flag=userMapper.insert( userDTO.toConvertUserEntity());
        if (flag!=1){
            throw new Exception();
        }
        return flag;
    }

    @Override
    public User loginAccount(UserReq req) {
        //1 先查询是否有已存在用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",req.getUserName());
        User checkUser  = userMapper.selectOne(queryWrapper);
        if(checkUser==null){
            throw new UserException(Error.USER_NOT_FOUND);
        }
        //2 密码加密
        String EncodePsd = DigestUtils.sha512Hex(req.getUserPassword());
        queryWrapper.eq("user_password",EncodePsd);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            throw new UserException(Error.USER_PASSWORD_FAIL);
        }
        //登陆成功返回用户信息
        return user;
    }

    @Override
    public List<User> listAllUser() {
        return  userMapper.selectList(new QueryWrapper<>());
    }
}
