package com.tj703.web_app_server_study;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return List.of();
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
        return 0;
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
