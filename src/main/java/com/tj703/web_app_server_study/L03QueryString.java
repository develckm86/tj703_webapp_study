package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/queryStringStudy.do")
public class L03QueryString extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setCharacterEncoding("utf-8");


        String queryString=req.getQueryString();
        //  name=a&age=30&id=33&gender=m
        String [] params=queryString.split("&");
        //  {name=a,age=30,id=33,gender=m}
        Map paramMap=new HashMap();
        for(String param:params){
            String [] p=param.split("=");
            paramMap.put(p[0],p[1]);
        }

        String name=req.getParameter("name");
        String ageStr=req.getParameter("age");
        //파라미터는 문자열과 정수를 구분하지 않는다. 모두 문자열이다.
        int age=Integer.parseInt(ageStr);
        //파라미터를 내가 사용할 타입으로 바꿔야한다.
        System.out.println(queryString);
        resp.setContentType("text/html");
        resp.getWriter().write("<h1>쿼리스트링</h1>");
        PrintWriter out=resp.getWriter();
        out.write("<ul>");
        out.write("<li> queryString :"+queryString+"</li>");
        out.write("<li> name :"+name+"</li>");
        out.write("<li> age :"+age+"</li>");
        out.write("<li> name :"+paramMap.get("name")+"</li>");
        out.write("<li> age :"+paramMap.get("age")+"</li>");
        out.write("</ul>");
    }
}
