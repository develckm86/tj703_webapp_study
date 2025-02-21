package com.tj703.web_app_server_study.model2_service.filter;

import com.tj703.web_app_server_study.model2_service.service.UserServiceImp;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //servletRequest.getCooke
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        Cookie[] cookies=req.getCookies();
        Cookie autoLogin=null;
        Cookie autoEmail=null;
        Cookie autoPw=null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                switch (c.getName()) {
                    case "auto_login": autoLogin=c;break;
                    case "auto_email": autoEmail=c;break;
                    case "auto_password": autoPw=c;break;
                }
            }
        }
        if(autoLogin!=null && autoLogin.equals(1)){
            try {
                //new UserServiceImp().login()
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
