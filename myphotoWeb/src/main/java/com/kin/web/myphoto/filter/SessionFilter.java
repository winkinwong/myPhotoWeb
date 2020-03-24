package com.kin.web.myphoto.filter;


import com.kin.web.myphoto.global.ThreadLocalHelper;
import com.kin.web.myphoto.pc.accountManager.entity.User;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       //每次请求时,都会获取session中user,在请求结束时移除线程用户
        HttpSession session = request.getSession();
        Object sessionUser = session.getAttribute("login_user");
        /*
        * 从session中获取user数据
        * */
       if(sessionUser!=null && sessionUser instanceof User){
           System.out.println("This is SessionFilter");
           ThreadLocalHelper.setSessionUser((User) sessionUser);
       }
        filterChain.doFilter(request,response);
       /*
       * 移除当前线程用户，避免数据泄露
       * */
       ThreadLocalHelper.removeSessionUser();
    }
}
