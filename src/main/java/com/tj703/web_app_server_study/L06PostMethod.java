package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//action 페이지, action 리소스 : 보여주는 용도의 리소스가 아니라 처리만하는 페이지
@WebServlet("/postStudy.do")
public class L06PostMethod extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //양식을 다시 제출하는 것을 확인하는 이유???
        //->post 로 통신한 페이지는 url에 파라미터가 없고 파라미터와 리소스가 대응되지 않기 때문에 새로고쳤을 때 원하는 리소스를 받을 수
        //없다고 경고를 보내는 것

        //GET : book 상세페이지 ?b_id=999 -> "경민 리액트 수업 : get 방식에서 파라미터는 리소스와 대응 => 공유할 수 있는 리소스 주소

        //POST : 회원가입 (id=경민, pw=1234) -> "경민"
        //POST : 회원가입 (id=경민, pw=1234?) -> "경민"
        //POST : 회원가입 (id=상혁, pw=1234) -> "상혁"???
        //http://localhost:9999/web_app_server_study_war_exploded/postStudy.do => 공유할 수 없는 리소스 주소
        //최경민으로 로그인하는 리소스 주소 ??
        //최경민으로 회원가입하는 리소스 주소 ??
        //GET POST 의 차이
        String userId=req.getParameter("userId");
        String password=req.getParameter("userPw");
//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out=resp.getWriter();
//        out.println("<h1>회원가입 성공 "+userId+"님 </h1>");
        boolean result=false;
        if(userId.length()>3 && password.length()>3){
            //jdbc 로 회원가입 시도
            result=true;
        }

        if(result){
            //응답 코드 (http status) 302
            resp.sendRedirect("./signupSuccess.html");
        }else{
            resp.sendRedirect("./signupFail.html");
        }

    }
}
