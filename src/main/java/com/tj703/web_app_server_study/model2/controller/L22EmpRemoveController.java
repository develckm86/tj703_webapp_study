package com.tj703.web_app_server_study.model2.controller;

import com.tj703.web_app_server_study.model2.dao.L16EmpDao;
import com.tj703.web_app_server_study.model2.dao.L16EmpDaoImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/model2/empRemove.do")
public class L22EmpRemoveController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empNoStr=req.getParameter("emp_no");
        L16EmpDao empDao=null;
        int delete=0; //삭제 하려하는데 이미 삭제되면 0 반환
        try {
            int empNo=Integer.parseInt(empNoStr);
            empDao=new L16EmpDaoImp();
            delete=empDao.deleteById(empNo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        } catch (Exception e) { //1.db 접속오류 (접속이 너무 몰렸을때)
            e.printStackTrace();
            //resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            //return;
        } finally { //한번은 무조건 실행하겠다.
            if (empDao!=null) {empDao.close();}
        }

        if(delete>0){ //삭제 성공
            resp.sendRedirect(req.getContextPath()+"/model2/empList.do");
        }else{
            resp.sendRedirect(req.getContextPath()+"/model2/empDetail.do?emp_no="+empNoStr);
        }

    }
}
