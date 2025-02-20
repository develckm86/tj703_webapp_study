package com.tj703.web_app_server_study.model2_service.controller;

import com.tj703.web_app_server_study.model2_service.dto.LoginLogDto;
import com.tj703.web_app_server_study.model2_service.dto.UserDto;
import com.tj703.web_app_server_study.model2_service.dto.UserServiceLoginDto;
import com.tj703.web_app_server_study.model2_service.service.UserServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/service/login.do")
public class UserLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/service/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //email=user1%40example.com&password=9999&auto_login=1&auto_email=1
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String autoLogin = req.getParameter("auto_login");
        String autoEmail = req.getParameter("auto_email");
        //로그인 컨트롤러에서 제일 중요한 부분
        //1.파라미터 받아오기
        //2.service 에게 해당 유저가 있는지 물어보기
        //3.만약 존재하면 서버에 존재하는 세션객체에 user 객체를 저장 -> main 으로 이동
        //4.만약에 존재하지 않으면 다시 로그인 폼으로 돌아가서 메세지(아이디나 비밀번호를 확인하세요!)
        //5.만약 중간에 오류 (1.500에러,2.로그인 중 오류가 발생했습니다. 다시시도하세요.3.에러메세지를 그대로 노출 X)

        String msg=null; //세션에 msg 보내서 view에서 msg가 있을때 alert 으로 경로 후 메세지 삭제
        //클라이언트가 접속한 ip와 브라우저 조회
        String ipAddress=req.getRemoteAddr();
        String userAgent=req.getHeader("User-Agent");
        String browser=req.getHeader("Sec-Ch-Ua");
        String [] agents=browser.split(",");
        System.out.println("ipAddress:"+ipAddress);
        System.out.println("userAgent:"+userAgent);
        System.out.println("browser:"+browser);

        UserServiceLoginDto loginDto=null;

        try {
            UserDto user=new UserDto();
            user.setEmail(email);
            user.setPassword(password);
            LoginLogDto log=new LoginLogDto();
            log.setIpAddress(ipAddress);
            log.setUserAgent(agents[1]);
            loginDto=new UserServiceImp().login(user,log);
            System.out.println(loginDto);
            //로그인 실패시 loginDto==null
        }catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500);
            return;
        }
        if(loginDto!=null && loginDto.getUser()!=null) {
            //로그인 성공
            HttpSession session=req.getSession();
            session.setAttribute("loginUser",loginDto.getUser());
            if(loginDto.isPwHistory()){
                resp.sendRedirect(req.getContextPath()+"/");
            }else {
                resp.sendRedirect(req.getContextPath()+"/service/pwModify.do");
            }
        }else{
            resp.sendRedirect(req.getContextPath()+"/service/login.do");
        }
    }
}
