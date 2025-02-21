package com.tj703.web_app_server_study.model2_service.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/service/logout.do")
public class UserLogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //logout : session에 저장된 loginUser 객체가 null일 때
        HttpSession session = req.getSession();
        //session.setAttribute("loginUser", null);
        session.removeAttribute("loginUser");
        //session.invalidate();
        //다수의 객체로 로그인을 할때는 session 을 완전히 만료시키는 방법도 있다.
        Cookie [] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("auto_login") || c.getName().equals("auto_email") || c.getName().equals("auto_password"))  {
                    c.setMaxAge(0);
                    c.setPath("/");
                    resp.addCookie(c);
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}