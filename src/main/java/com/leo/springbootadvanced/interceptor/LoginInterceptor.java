package com.leo.springbootadvanced.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    //實現攔截器
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);
        if(request.getSession().getAttribute("user") == null){
            response.sendRedirect("/login");    //跳轉回login頁面
            return false;
        }
        return true;
    }
}
