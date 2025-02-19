package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/setSession.do")
public class L19Session extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Session : 서버에 저장되는 객체
        //요청하는 클라이언트(브라우저)에 대응해서 생성되는 세션객체
        //클라이언트가 자바서버에 요청하면 최초 요청때 톰캣이 JSESSIONID 라는 쿠키를 만들고
        //JSESSIONID 값으로 고유값을 만들어 배포한다.(JSESSIONID==클라이언트의 고유번호)
        //JSESSIONID 값을 키로 갖는 Session 객체를 서버에 생성
        //**클라이언트가 서버에 요청하면 요청 정보에 있는 JSESSIONID 쿠키로 대응되는 Session
        //찾아서(최초에는 생성) 동적리소스(servlet)의 request 객체에 추가해서 전달
        //Session vs Cookie
        //cookie 는 브라우저에 저장되는 데이터
        //session 은 서버에 저장되는 객체
        HttpSession session=req.getSession();
        //톰캣이 클라이언트와 대응되게 생성한 세션 객체를 반환
        session.setAttribute("email","devekckm@gmail.com");
        session.setAttribute("name","경민");
        session.setAttribute("married",true);
        session.setAttribute("pw",1234);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>session 에 email,name,married,pw 저장 완료!</h1>");
        out.println("<a href='./'>메인페이지로 이동</a>");
    }
}
