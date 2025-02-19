package com.tj703.web_app_server_study.model2_service.dao;

import com.tj703.web_app_server_study.model2_service.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImp implements UserDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public UserDaoImp(Connection conn) {
        this.conn=conn;
        //conn을 직접 생성하지 않고 사용하는 쪽에서 만들어저 전달
        //(이유: 서비스 로직에서 접속 후 여러쿼리를 실행하기 위해, 트랜잭션 구현)
    }

    @Override
    public UserDto findByUserIdAndPassword(int id, String pw) throws Exception {
        UserDto user = null;
        String sql="select * from users where user_id=? and password=?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setString(2, pw);
        rs = ps.executeQuery();
        if(rs.next()) {
            user = new UserDto();
            user.setUserId(rs.getInt("user_id"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setCreatedAt(rs.getString("created_at"));
        }
        return user;
    }

    @Override
    public UserDto findByEmailAndPassword(String email, String pw) throws Exception {
        UserDto user = null;
        String sql="select * from users where email=? and password=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, pw);
        rs = ps.executeQuery();
        if(rs.next()) {
            user = new UserDto();
            user.setUserId(rs.getInt("user_id"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setCreatedAt(rs.getString("created_at"));
        }
        return user;

    }

    @Override
    public int updateSetPwByEmail(UserDto user) throws Exception {
        int rows=0;
        String sql="update users set password=? where email=?";
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getPassword());
        ps.setString(2, user.getEmail());
        rows=ps.executeUpdate();
        return rows;
    }

    @Override
    public int insert(UserDto user) throws Exception {
        int insert=0;
        String sql="INSERT  INTO users ( email, password ) VALUES (?,?)";
        ps = conn.prepareStatement(sql);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        insert = ps.executeUpdate();
        return insert;
    }
}
