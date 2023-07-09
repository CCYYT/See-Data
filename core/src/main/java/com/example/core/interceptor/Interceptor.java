package com.example.core.interceptor;

import com.example.core.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Interceptor implements HandlerInterceptor {

    @Override
//    此方法在处理请求之前实现
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//       判断用户是否登录？判断session里有没有user实例
        HttpSession session = request.getSession();
        String userUuid = ((User) session.getAttribute("userUuid")).getUuid();
        if (userUuid == null) {
//            空就是没有登陆或者登陆失败
            response.sendRedirect( "/pages/login.html");
            return false;
        }
//        放行可以访问其他页面
        return true;
    }
}
