package com.tj703.web_app_server_study;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class L16EmpDaoImp implements L16EmpDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public L16EmpDaoImp() throws Exception {
        conn=L15SingletonDB.getConnection(); //해당 클래스가 객체가 될때 무조건 필드가 존재함
    }

    @Override
    public List<L17EmpDto> findAll() throws Exception {
        List<L17EmpDto> empList=null; //array[] list
        String sql="select * from employees limit 0,100";
        ps=conn.prepareStatement(sql);
        rs=ps.executeQuery();
        empList=new ArrayList<>();
        while(rs.next()) {
            L17EmpDto emp=new L17EmpDto();
            emp.setEmpNo(rs.getInt("emp_no"));
            emp.setBirthDate(rs.getString("birth_date"));
            emp.setFirstName(rs.getString("first_name"));
            emp.setLastName(rs.getString("last_name"));
            emp.setGender(rs.getString("gender"));
            emp.setHireDate(rs.getString("hire_date"));
            empList.add(emp);
        }
        return empList;
    }

    @Override
    public L17EmpDto findById(int empNo) throws Exception {
        L17EmpDto findById=null;
        String sql = "select * from employees where emp_no=?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, empNo);
        rs = ps.executeQuery();
        if(rs.next()) {
            findById=new L17EmpDto();
            findById.setEmpNo(rs.getInt("emp_no"));
            findById.setFirstName(rs.getString("first_name"));
            findById.setLastName(rs.getString("last_name"));
            findById.setBirthDate(rs.getString("birth_date"));
            findById.setGender(rs.getString("gender"));
            findById.setHireDate(rs.getString("hire_date"));
        }
        return findById;
    }

    @Override
    public int insert(L17EmpDto emp) throws Exception {
        int insert=0;
        String sql="INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date)  values (?,?,?,?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, emp.getEmpNo());
        ps.setString(2, emp.getBirthDate());
        ps.setString(3, emp.getFirstName());
        ps.setString(4, emp.getLastName());
        ps.setString(5, emp.getGender());
        ps.setString(6, emp.getHireDate());
        insert=ps.executeUpdate(); //성공시 등록한 수가 반환 1
        return insert;
    }

    @Override
    public int update(L17EmpDto emp) throws Exception {
        return 0;
    }

    @Override
    public int deleteById(int empNo) throws Exception {
        return 0;
    }
}
