package com.kin.web.myphoto.pc.accountManager.controller;


import com.kin.web.myphoto.exception.Error;
import com.kin.web.myphoto.exception.UserException;
import com.kin.web.myphoto.global.ResultBean;
import com.kin.web.myphoto.global.ThreadLocalHelper;
import com.kin.web.myphoto.pc.accountManager.dto.UserDTO;
import com.kin.web.myphoto.pc.accountManager.entity.User;
import com.kin.web.myphoto.pc.accountManager.req.UserReq;
import com.kin.web.myphoto.pc.accountManager.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.kin.web.myphoto.base.BaseController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Kin
 * @since 2020-02-02
 */
@RestController
@RequestMapping("/accountManager/user")
public class UserController extends BaseController {
    private static final Logger logger=LoggerFactory.getLogger(UserController.class);
    @Resource
    private IUserService userService;

    //注册账号
    @PostMapping("/registerAccount")
    public ResultBean registerAccount(@RequestBody UserDTO userDTO) throws Exception {
        logger.info("注册账号 user:",userDTO);
        userService.registerAccount(userDTO);
        return ResultBean.success();
    }
    //登录
    @PostMapping("/loginAccount")
    public ResultBean loginAccount(@RequestBody UserReq req, HttpServletRequest request){
        User user = userService.loginAccount(req);
        logger.info("用户登陆 user:",user.getUserId());
        //将登陆信息放入session
        HttpSession session = request.getSession();
        //这个让前端获取的登陆信息
        session.setAttribute("login_user",user);
        return ResultBean.success("登陆成功");
    }
    //登出
    @GetMapping("/logoutAccount")
    public ResultBean logoutAccount(HttpServletRequest request){
        User user = ThreadLocalHelper.getSessionUser();
        if (user==null){
            throw new UserException(Error.USER_LOGOUT);
        }
        logger.info("用户登出 user:",user.getUserId());
        HttpSession session = request.getSession();
        session.invalidate();
        return ResultBean.success("已登出");
    }
}
