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
import com.mysql.cj.jdbc.Driver;
@WebServlet("/empSignupAction.do")
public class L08EmpSignupAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //emp를 등록하는 action 페이지
        //1.양식데이터 받기
        //2.양식데이터 처리(jdbc)
        //3.redirect로 페이지 이동
        String empNoStr = req.getParameter("emp_no");
        String birthDate = req.getParameter("birth_date");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String gender = req.getParameter("gender");
        String hireDate = req.getParameter("hire_date");
        //emp_no=1&first_name=%EA%B2%BD%EB%AF%BC&last_name=%EC%B4%88%EC%9D%B4&gender=M&birth_date=1986-05-25&birth_date=2013-06-21
        String url="jdbc:mysql://localhost:3306/employees";
        String username = "root";
        String password = "mysqlmysql";
        String driver = "com.mysql.cj.jdbc.Driver";
        String sql="INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) " +
                "values " +
                "(?,?,?,?,?,?)";
        int insert=0; //dml(insert,update,delete => int)

        try {Class.forName(driver);} catch (ClassNotFoundException e) {throw new RuntimeException(e);}
        try(
                Connection conn= DriverManager.getConnection(url,username,password);
                PreparedStatement ps=conn.prepareStatement(sql);
                )
        {
            int empNo = Integer.parseInt(empNoStr);
            ps.setInt(1,empNo);
            ps.setString(2,birthDate);
            ps.setString(3,firstName);
            ps.setString(4,lastName);
            ps.setString(5,gender);
            ps.setString(6,hireDate);
            insert=ps.executeUpdate(); //executeUpdate dml 을 실행하는 함수
            //오류가 발생하는 이유
            //NumberFormatException : empNo를 안보냈거나 문자열로 잘못 작성
            //SQL : 접속오류, 쿼리오류, preparedStatemetn 오류,
            // ** empno 중복, 타입이 맞지 않을 때, 길이가 넘었을때 => 양식데이터를 확인하고 다시 가입시도
        }catch (Exception e){
            e.printStackTrace();
            //resp.sendError(500);
            //return;
        }
        //오류가 발생하거나 등록이 안되면 insert=0 -> empSignup.html 이동
        //insert=1 -> 등록성공 empList(empCRUD.do) 로 이동
        if(insert>0){
            resp.sendRedirect("./empCRUD.do");
        }else{
            resp.sendRedirect("./empSignup.html");
        }

    }
}
