package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

@WebServlet("/empModify.do")
public class L10EmpModify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //수정페이지는 상세와 서비스(==db) 구현은 같은데 뷰만 다르다.
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
            conn= DriverManager.getConnection(url,username,password);
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
            out.println("<h1>사원 수정</h1>");
            out.println("<form action=\"empModify.do\" method=\"post\">");
            out.println("<p><label>사원 번호 : "+emp.get("emp_no")+"<input type='hidden' value='"+emp.get("emp_no")+"' name='emp_no'></label></p>");
            out.println("<p><label>사원 이름 : <input value='"+emp.get("first_name")+"' name='first_name'></label></p>");
            out.println("<p><label>사원 성씨 : <input value='"+emp.get("last_name")+"' name='last_name'></label></p>");
            out.println("<p>사원 성별 : <label>남성 : <input value='M' "+( (emp.get("gender").equals("M")) ? "checked" : "")+" type=radio name='gender'></label>");
            out.println("<label>여성 : <input value='F' "+( (emp.get("gender").equals("F")) ? "checked" : "")+" type=radio name='gender'></label></p>");

            out.println("<p><label>사원 생일 : <input type='date' value='"+emp.get("birth_date")+"' name='birth_date'></label></p>");
            out.println("<p><label>사원 입사일 : <input type='date'  value='"+emp.get("hire_date")+"' name='hire_date'></label></p>");
            out.println("<p><button type='reset'>초기화</button> &nbsp; <button>제출</button></p>");
            out.println("</form>");
        }
    }
}
