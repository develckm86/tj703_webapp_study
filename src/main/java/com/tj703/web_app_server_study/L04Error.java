package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/errorStudy.do")
public class L04Error extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //서버가 잘못되면 500에러 : 개발자 실수로 발생하는 오류,서버에 문제가 생겼을 때
        //throw new ServletException(); //에러를 발생해서 위임처리(나를 실행하는 곳에서 오류를 발생시켜라!)
        //int i=Integer.parseInt("삼십삼"); //runtime error => 심각한 요류(예외처리)

        //클라이언트에게 개발자나 서버가 요청실수를 알려주는 코드 400
        //405 : 잘못된 방식으로 서블릿을 요청함
        //404 : 클라이언트가 잘못된 url 로 서버에 요청했을 때
        //400 : 보내야할 매개변수가 없을 때 발생시키는 오류
        // book/detail.do?b_id=13   => "13"
        // book/detail.do?b_id=     => ""
        // book/detail.do?          => null
        // book/detail.do?b_id=d13  => "d13"

        String bIdStr=request.getParameter("b_id");
        try{
            int bId=Integer.parseInt(bIdStr);
            //id가 꼭 필요한데 보내지 않아거나 잘못보내면 페이지가 동작할 수 없다 =>400(BAD_REQUEST)
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<h1>북 아이디가 "+bId+"인 책을 불러오겠습니다. </h1>");
        }catch (NumberFormatException e){
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;//함수 종료
            //response.setStatus(400);
        }

    }
}
