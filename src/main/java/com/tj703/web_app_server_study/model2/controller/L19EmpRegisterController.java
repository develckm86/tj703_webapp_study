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

@WebServlet("/model2/empRegister.do")
public class L19EmpRegisterController extends HttpServlet {
    //get -> 등록폼
    //post -> 등록액션
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/empRegister.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //컨트롤러 : 요청(파라미터)과 응답 처리(redirect)
        L17EmpDto emp=new L17EmpDto();
        //emp.setEmpNo(Integer.parseInt(req.getParameter("emp_no")));
        int insert=0;
        L16EmpDao empDao=null;
        try {
            emp.setEmpNo(Integer.parseInt(req.getParameter("emp_no")));
            emp.setFirstName(req.getParameter("first_name"));
            emp.setLastName(req.getParameter("last_name"));
            emp.setGender(req.getParameter("gender"));
            emp.setBirthDate(req.getParameter("birth_date"));
            emp.setHireDate(req.getParameter("hire_date"));
            empDao=new L16EmpDaoImp();
            insert=empDao.insert(emp);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            //오류: db생성되지 않은경우, 파라미터가 잘못된경우,....???
            e.printStackTrace();
        }finally { //한번은 무조건 실행하겠다.
            if (empDao!=null) {empDao.close();}
        }

        if(insert>0){
            resp.sendRedirect(req.getContextPath()+"/model2/empList.do");
        }else{
            resp.sendRedirect(req.getContextPath()+"/model2/empRegister.do");
        }


    }
}
