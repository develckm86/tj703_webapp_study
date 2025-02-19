package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getCookie.do")
public class L16GetCookies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //해당서버에 요청할때마다 쿠키가 포함되서 간다.
        Cookie [] cookies =req.getCookies();
        String id = null;
        String pw = null;
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("email")){
                id = cookie.getValue();
            }else if(cookie.getName().equals("password")){
                pw = cookie.getValue();
            }
        }
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>쿠키 불러오기</h1>");
        out.println("<p>email:"+id+"</p>");
        out.println("<p>pw:"+pw+"</p>");

    }
}
