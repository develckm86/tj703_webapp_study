package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/setBannerCookie.do")
public class L18SetBannerCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //~~~~
        String isBannerCookie = req.getParameter("isBannerCookie"); //"1"
        resp.setContentType("text/html");
        Cookie cookie = new Cookie("isBannerCookie", isBannerCookie);
        cookie.setMaxAge(60*60*24);
        resp.addCookie(cookie);
        PrintWriter out = resp.getWriter();
        out.println("<script> window.close();</script>");


    }
}
