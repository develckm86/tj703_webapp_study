package com.tj703.web_app_server_study.model2_service.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//servlet 에 가기 전에 동작 (servlet을 특정 짓지 않고 요청하는 url 패턴으로 실행가능)
// * : wild card(조커), 모든
@WebFilter("/*")//:모든 요청의 서블릿을 실행하기 전에 EncodingFilter 필터를 실행하라!
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //(request,response,filterChain)
        //System.out.println("EncodingFilter '/*' ");
        servletRequest.setCharacterEncoding("UTF-8");
        //파라미터의 한글 데이터를 UTF-8로 변경
        filterChain.doFilter(servletRequest, servletResponse);
        //doFilter 원래 요청하던 서블릿을 실행!
    }
}
