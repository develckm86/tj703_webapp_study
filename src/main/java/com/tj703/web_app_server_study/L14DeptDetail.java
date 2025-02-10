package com.tj703.web_app_server_study;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/l14deptDetail.do")
public class L14DeptDetail extends HttpServlet {
    public static final String URL = "jdbc:mysql://localhost:3306/employees";
    public static final String USER = "root";
    public static final String PASSWORD = "mysqlmysql";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static Connection conn;
    public static PreparedStatement pstmt;
    public static ResultSet rs;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptNo=req.getParameter("dept_no");
        if(deptNo==null && deptNo.equals("")){resp.sendError(400); return;}
        Map<String,String> dept=null;
        String sql="SELECT * FROM departments WHERE dept_no=?";
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, deptNo);
            rs = pstmt.executeQuery();
            if(rs.next()){
                dept=new HashMap<>();
                dept.put("dept_no", rs.getString("dept_no"));
                dept.put("dept_name", rs.getString("dept_name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(dept!=null){ //조회와 동시에 삭제되지 않았거나 오류가 발생하지 않을때
            //view 를 렌더링할 jsp 객체를 전달
            req.setAttribute("dept",dept);
            req.getRequestDispatcher("L14DeptDetail.jsp").forward(req, resp);
            //jsp forward 방식으로 view를 렌더링
            //forward 방식 : 요청과 응답을 view 페이지에 위임
            //최신 view template 동작 원리 : 렌더링한 html 만 반환

        }else{
            resp.sendRedirect("./L13DeptList.jsp"); //status 302
        }
        //redirect 와 forward 의 차이
        //redirect : 서버 내의 다른 리소스를 요청해서 이동 -> 다른 페이지로 변화
        //forward : sevlet 동적페이지가 view로 사용할 jsp 를 포함시켜서 동작 
    }
}
