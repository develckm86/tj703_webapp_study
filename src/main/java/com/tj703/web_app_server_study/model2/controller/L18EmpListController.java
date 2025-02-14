package com.tj703.web_app_server_study.model2.controller;

import com.tj703.web_app_server_study.model2.dao.L16EmpDao;
import com.tj703.web_app_server_study.model2.dao.L16EmpDaoImp;
import com.tj703.web_app_server_study.model2.dto.L17EmpDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/model2/empList.do")
public class L18EmpListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //webapp/WEB-INF : web.xml DD (개발자가 배포에 대한 설정하는 문서)를 두는 곳으로 숨겨진 폴더
        //숨겨진 폴더 : private 처럼 클라이언트는 접근할 수 없고 내부적으로 접근 가능
        // jsp : java 와 동일한 코드를 실행할 수 있어서 보안에 취약
        //webapp/ 하위는 내부적으로 root 경로가 된다.

        //**jsp 문서를 WEB-INF 하위에 두나요? 면접
        //뷰템플릿도 백엔드 언어기 때문에 외부에서 접근할 수 없는 경로인 WEB-INF 하위에 두고 내부에서만 접근합니다.
        L16EmpDao empDao=null;
        List<L17EmpDto> empList=null;
        try {
            empDao=new L16EmpDaoImp();
            empList=empDao.findAll();
        } catch (Exception e) {
            //throw new RuntimeException(e);
            //list에서 오류가 발생할 이유는 db 문제기 때문에 500으로 서비스를 멈춘다.
            e.printStackTrace();
            resp.sendError(500);
            return;
        }finally { //한번은 무조건 실행하겠다.
            if (empDao!=null) {empDao.close();}
        }
        //forward 될때는 무조건 empList 는 null 이 아니다.
        req.setAttribute("empList", empList);
        req.getRequestDispatcher("/WEB-INF/views/empList.jsp").forward(req, resp);


    }
}
