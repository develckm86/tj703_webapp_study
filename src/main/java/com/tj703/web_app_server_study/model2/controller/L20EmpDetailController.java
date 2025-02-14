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

@WebServlet("/model2/empDetail.do")
public class L20EmpDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //http://localhost:9999/web_app_server_study_war_exploded/model2/model2/empDetail.do?emp_no=11
        String empNoStr=req.getParameter("emp_no");
        L17EmpDto emp=null;
        L16EmpDao dao=null;
        try {
            int empNo=Integer.parseInt(empNoStr);
            dao=new L16EmpDaoImp();
            emp=dao.findById(empNo);
        }catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500);
            return;
        }finally { //한번은 무조건 실행하겠다.
            if (dao!=null) {dao.close();}
        }

        if(emp==null) { //조회와 동시에 삭제된 경우 -> list 로 이동
            resp.sendRedirect("./empList.do");
        }else{
            req.setAttribute("emp", emp);
            req.getRequestDispatcher("/WEB-INF/views/empDetail.jsp").forward(req, resp);
        }


    }
}
