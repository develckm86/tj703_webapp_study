package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;

@WebServlet("/empDetail.do")
public class L09EmpDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empNoStr=req.getParameter("emp_no");
        String sql="SELECT * FROM employees WHERE emp_no=?";
        String url="jdbc:mysql://localhost:3306/employees";
        String username="root";
        String password="mysqlmysql";
        String driver="com.mysql.cj.jdbc.Driver";
        //model1 : 동적페이지에서 접속하고 출력하는 모든 코드가 모여있는 디자인(재사용코드 x)
        //model2 : Model(service,DAO) View(html) Controller(Servlet)
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        HashMap<String,Object> emp=null;
        try{
            int empNo=Integer.parseInt(empNoStr);
            Class.forName(driver);
            conn=DriverManager.getConnection(url,username,password);
            ps=conn.prepareStatement(sql);
            ps.setInt(1,empNo);
            rs=ps.executeQuery();
            if(rs.next()){
                emp=new HashMap<>();
                emp.put("emp_no",rs.getInt("emp_no"));
                emp.put("first_name",rs.getString("first_name"));
                emp.put("last_name",rs.getString("last_name"));
                emp.put("gender",rs.getString("gender"));
                emp.put("birth_date",rs.getString("birth_date"));
                emp.put("hire_date",rs.getString("hire_date"));
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            resp.sendError(400); //있어야할 파라미터가 없어
            return;
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(500);
            return;
        }
        if(emp!=null){
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out=resp.getWriter();
            out.println("<h1>사원 상세</h1>");
            out.println("<p>사원 번호 : "+emp.get("emp_no")+"</p>");
            out.println("<p>사원 이름 : "+emp.get("first_name")+"</p>");
            out.println("<p>사원 성씨 : "+emp.get("last_name")+"</p>");
            out.println("<p>사원 성별 : "+emp.get("gender")+"</p>");
            out.println("<p>사원 생일 : "+emp.get("birth_date")+"</p>");
            out.println("<p>사원 입사일 : "+emp.get("hire_date")+"</p>");
        }
    }
}
