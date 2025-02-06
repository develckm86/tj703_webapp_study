package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/empCRUD.do")
public class L07EmpCrudList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url="jdbc:mysql://localhost:3306/employees";
        String username="root";
        String password="mysqlmysql";
        String driver="com.mysql.cj.jdbc.Driver";
        String sql="SELECT * FROM employees ORDER BY emp_no ASC LIMIT 0,10 ";
        List<Map> empList=null;
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try{
            Class.forName(driver);
            conn=DriverManager.getConnection(url,username,password);
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
            empList=new ArrayList<>();
            while(rs.next()){
                Map emp=new HashMap();
                emp.put("emp_no",rs.getInt("emp_no"));
                emp.put("first_name",rs.getString("first_name"));
                emp.put("last_name",rs.getString("last_name"));
                emp.put("gender",rs.getString("gender"));
                emp.put("birth_date",rs.getString("birth_date"));
                emp.put("hire_date",rs.getString("hire_date"));
                empList.add(emp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(rs!=null){try {rs.close();} catch (SQLException e) {}}
            if(stmt!=null){try{stmt.close();}catch (SQLException e){}}
            if(conn!=null){try{conn.close();}catch (SQLException e){}}
        }



        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<h1>사원 관리 페이지</h1>");
        out.println("<hr>");
        out.println("<h2>사원 리스트</h2>");
        out.println("<a href='./empSignup.html'>사원 등록 폼</a>");
        out.println("<hr>");
        out.println(empList);
        out.println("</html>");
    }
}
