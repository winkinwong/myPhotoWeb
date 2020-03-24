package com.kin.web.myphoto.pc.accountManager.service;

import com.kin.web.myphoto.pc.accountManager.dto.UserDTO;
import com.kin.web.myphoto.pc.accountManager.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kin.web.myphoto.pc.accountManager.req.UserReq;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
public interface IUserService extends IService<User> {
    int registerAccount(UserDTO userDTO) throws Exception;
    User loginAccount(UserReq req);
    List<User> listAllUser();
}
