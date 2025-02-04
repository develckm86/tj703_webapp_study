package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/resStudy.do") //서버에서 새로운 주소를 등록해야하기 때문에 서버 재시작
public class L01Response extends HttpServlet {
    //405 : 허용되지 않는 method
    //브라우저가 서버에 요청하는 방법(method) 2가지
    //1.GET : url 을 요청하는 방법(link 누를 때)
    //2.POST : 양식에서 submit 버튼을 누를 때
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //HttpServletRequest req, HttpServletResponse resp
        //HttpServletRequest request : 요청정보(url,cookie,parameter(?~~))
        //HttpServletResponse response : 응답하는 내역 (문자열->문서화)
        // HttpServletResponse.out(PrintWrite) 에 문자열을 작성하면 자동으로 응답
//        resp.setContentType("text/html");
//        resp.getWriter().write("<html>");
//        resp.getWriter().write("<h1>안녕 서블릿 응답!</h1>");
//        resp.getWriter().write("</html>");
        resp.setContentType("application/json");
        resp.getWriter().write("{\"id\":\"develckm86\"}");
    }
}
