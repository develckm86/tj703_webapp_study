package com.tj703.web_app_server_study;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//http://localhost:9999/web_app_server_study_war_exploded/hello-servlet
@WebServlet("/hello-servlet") //컴파일시 해당 class 를 /hello-servlet 로 등록하겠다.(==동적리소스를 만들겠다.)
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "안녕 서블릿(java 동적리소스)!!";
    }
    //http://localhost:9999/web_app_server_study_war_exploded/hello-servlet 를 브라우저에 입력하면
    //class HelloServlet.doGet()을 실행
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }
    public void destroy() {
    }
}