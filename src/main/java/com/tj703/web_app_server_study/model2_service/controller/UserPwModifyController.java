package com.tj703.web_app_server_study.model2_service.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/service/pwModify.do")
public class UserPwModifyController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/service/pwModify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pw=req.getParameter("pw");
        HttpSession session = req.getSession();
        Object loginUserObj=session.getAttribute("loginUser");
        String msg=null;
        if(loginUserObj==null){
            msg="로그인 후 비밀번호를 변경하세요!";
            session.setAttribute("msg",msg);
            resp.sendRedirect(req.getContextPath()+"/service/login.do");
            return;
        }

    }
}