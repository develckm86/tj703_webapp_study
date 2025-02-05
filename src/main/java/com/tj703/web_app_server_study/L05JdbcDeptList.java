package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
//import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.*;

@WebServlet("/jdbcDeptList.do")
public class L05JdbcDeptList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url="jdbc:mysql://localhost:3306/employees";
        String username="root";
        String password="mysqlmysql";
        String driver="com.mysql.cj.jdbc.Driver";
        String sql="select * from departments";
        List<Map<String, Object>> deptList=null;
        try{
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,username,password);
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            deptList=new ArrayList<>();

            while(rs.next()){
                Map<String,Object> map=new HashMap<>();
                map.put("dept_no",rs.getString("dept_no"));
                map.put("dept_name",rs.getString("dept_name"));
                deptList.add(map);
            }
        } catch (Exception e) {
            //resp.sendError(500);
            //return;
            throw new RuntimeException(e);
            //**** 서버의 중요한 내역을 에러로 배포하면 해킹의 위험이 있다.!
        }
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("<html>");
        out.print("<h1>jdbc 부서 리스트</h1>");
        out.print("<table><tr><th>부서번호</th><th>이름</th><th>상세페이지</th></tr>");
        for(Map<String,Object> map:deptList){
            out.print("<tr>");
            out.print("<td>"+map.get("dept_no")+"</td>");
            out.print("<td>"+map.get("dept_name")+"</td>");
            out.print("<td><a href=./detpDetail.do?dept_no="+map.get("dept_no")+">상세</a></td>");
            //http://localhost:9999/web_app_server_study_war_exploded/detpDetail.do?dept_no=d009
            out.print("</tr>");
        }
        out.print("</table>");
        out.print("</html>");
    }
}
