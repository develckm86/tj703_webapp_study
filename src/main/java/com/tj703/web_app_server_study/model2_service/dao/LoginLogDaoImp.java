package com.tj703.web_app_server_study.model2_service.dao;

import com.tj703.web_app_server_study.model2_service.dto.LoginLogDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginLogDaoImp implements LoginLogDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public LoginLogDaoImp(Connection conn) {
        this.conn = conn;
    }
    @Override
    public int insert(LoginLogDto dto) throws Exception{
        int result = 0;
        String sql = "insert into login_logs (user_id, ip_address, user_agent) values(?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, dto.getUserId());
        ps.setString(2, dto.getIpAddress());
        ps.setString(3, dto.getUserAgent());
        result = ps.executeUpdate();
        return result;
    }

    @Override
    public List<LoginLogDto> findAll() throws Exception{
        List<LoginLogDto> findAll=null;
        String sql="SELECT * FROM login_logs limit 0,100";
        ps= conn.prepareStatement(sql);
        rs=ps.executeQuery();
        findAll=new ArrayList<>();
        while(rs.next()){
            LoginLogDto dto=new LoginLogDto();
            dto.setLogId(rs.getInt("log_id"));
            dto.setIpAddress(rs.getString("ip_address"));
            dto.setUserAgent(rs.getString("user_agent"));
            dto.setLoginTime(rs.getString("login_time"));
            dto.setUserId(rs.getInt("user_id"));
            findAll.add(dto);
        }
        return findAll;
    }
}
