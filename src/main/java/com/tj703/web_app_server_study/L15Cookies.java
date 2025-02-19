package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/setCookie.do")
public class L15Cookies extends HttpServlet {
    //쿠키를 만들어서 브라우저에 남겨놓는 페이지
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //cookie 브라우저에 저장되는 문자열된 데이터 조각
        //서버가 브라우저에 응답하면서 남겨놓는 데이터
        //브라우저가 서버에 요청할때 마다 해당 서버가 남겨놓은 쿠키를 요청정보에 포함
        //쿠키는 만료시간이 있어서 해당 시간이 지나면 삭제된다.

        Cookie cookie = new Cookie("email", "servieSignup@example.com");
        Cookie cookie2 = new Cookie("password", "5555");
        //cookie.setPath("/"); //localhost:8080/ *  이경로에서 유요한 쿠키
        // default 경로 : localhost:8080/contextPath/ * 이경로에서 유요한 쿠키
        //cookie.setMaxAge(0); //삭제
        cookie.setMaxAge(5*60); //5분
        cookie2.setMaxAge(5*60);
        resp.addCookie(cookie);
        resp.addCookie(cookie2);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>쿠키생성 email/password</h1>");

    }
}
