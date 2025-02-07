package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/empRemoveAction.do")
public class L11EmpRemoveAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empNoStr = req.getParameter("emp_no");
        String url="jdbc:mysql://localhost:3306/employees";
        String username="root";
        String password="mysqlmysql";
        String driver="com.mysql.cj.jdbc.Driver";
        String sql="delete from employees where emp_no=?";
        Connection conn=null;
        PreparedStatement ps=null;
        int delete=0;
        try {
            int empNo = Integer.parseInt(empNoStr);
            Class.forName(driver);
            conn= DriverManager.getConnection(url,username,password);
            ps=conn.prepareStatement(sql);
            ps.setInt(1,empNo);
            delete=ps.executeUpdate();
        }catch (NumberFormatException e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(delete>0){
            resp.sendRedirect("./empCRUD.do");
        }else{
            //삭제 실패-> 오류, 먼저 삭제된 경우
            resp.sendRedirect("./empModify.do?empNo="+empNoStr);
        }
    }
}
