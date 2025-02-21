package com.tj703.web_app_server_study.model2_service.filter;

import com.tj703.web_app_server_study.model2_service.dto.LoginLogDto;
import com.tj703.web_app_server_study.model2_service.dto.UserDto;
import com.tj703.web_app_server_study.model2_service.dto.UserServiceLoginDto;
import com.tj703.web_app_server_study.model2_service.service.UserServiceImp;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

        if(req.getSession().getAttribute("autoLogin") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
            //로그인이 되어 있으면 자동로그인을 하지 않음
        }

        if (cookies != null) {
            for (Cookie c : cookies) {
                switch (c.getName()) {
                    case "auto_login": autoLogin=c;break;
                    case "auto_email": autoEmail=c;break;
                    case "auto_password": autoPw=c;break;
                }
            }
        }
        System.out.println("autoLogin:"+autoLogin.getValue());
        System.out.println("autoEmail:"+autoEmail.getValue());
        System.out.println("autoPw:"+autoPw.getValue());
        if(autoLogin!=null && autoLogin.getValue().equals("1")){
            try {
                String ip=req.getRemoteAddr();
                String agent=req.getHeader("Sec-Ch-Ua").split(",")[1];
                UserDto user=new UserDto();
                user.setEmail(autoEmail.getValue());
                user.setPassword(autoPw.getValue());
                LoginLogDto loginLog=new LoginLogDto();
                loginLog.setIpAddress(ip);
                loginLog.setUserAgent(agent);
                //new UserServiceImp().login()
                UserServiceLoginDto loginDto=new UserServiceImp().autoLogin(user,loginLog);
                System.out.println("loginDto:"+loginDto);
                if(loginDto!=null && loginDto.getUser()!=null){
                    //로그인 성공
                    HttpSession session=req.getSession();
                    session.setAttribute("loginUser",loginDto.getUser());
                    if(!loginDto.isPwHistory()){
                        HttpServletResponse resp=((HttpServletResponse)servletResponse);
                        resp.sendRedirect(req.getContextPath()+"/service/pwModify.do");
                        return;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
