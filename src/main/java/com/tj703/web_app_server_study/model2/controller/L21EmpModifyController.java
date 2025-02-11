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

@WebServlet("/model2/empModify.do")
public class L21EmpModifyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        L17EmpDto emp=null;
        int update=0;
        L16EmpDao empDao=null;
        String empNoStr=req.getParameter("emp_no");
        String successUrl= req.getContextPath()+"/model2/empList.do";
        String errorUrl= req.getContextPath()+"/model2/empDetail.do?emp_no="+empNoStr;
        try {
            emp=new L17EmpDto();
            emp.setEmpNo(Integer.parseInt(empNoStr));
            emp.setHireDate(req.getParameter("hire_date"));
            emp.setGender(req.getParameter("gender"));
            emp.setFirstName(req.getParameter("first_name"));
            emp.setLastName(req.getParameter("last_name"));
            emp.setBirthDate(req.getParameter("birth_date"));
            empDao=new L16EmpDaoImp();
            update=empDao.update(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //삭제는 직접 구현해보세요~
        if(update>0){
            resp.sendRedirect(successUrl);
        }else{
            resp.sendRedirect(errorUrl);
        }
    }
}
