package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/reqStudy.do")
public class L02Request extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //클라이언트가 서버에 요청할때 전달하는 정보 request(이런 내역 주세요!)
        String url = req.getRequestURL().toString();
        // http://localhost:9999/web_app_server_study_war_exploded/reqStudy.do
        // =>url 동적리소스의 전체 주소
        String uri = req.getRequestURI();
        // /web_app_server_study_war_exploded/reqStudy.do
        // =>uri 는 서버에서 동적리소스의 위치 == path
        String contextPath = req.getContextPath();//톰캣에서만 사용됨
        // /web_app_server_study_war_exploded
        // =>context path : 톰캣은 한번에 여러 웹앱을 실행하는데 해당 웹앱에 경로를 추가하는 것
        String queryString = req.getQueryString();
        System.out.println(url);
        System.out.println(uri);
        System.out.println(contextPath);
        System.out.println(queryString);
        //http://localhost:9999/web_app_server_study_war_exploded/reqStudy.do?name=%EA%B2%BD%EB%AF%BC&age=39
        //name=%EA%B2%BD%EB%AF%BC&age=39
        //url+ ?~~~ 쿼리스트링 : 동적리소스의 동적인 동작을 위해 매개변수를 전달
        //동적리소스 == 함수
        //function name==url (매개변수==queryString){
        //    return; == response
        //}
        //public HttpServetResponse (text/html) reqStudy.do(String name,ing age)
    }
}
