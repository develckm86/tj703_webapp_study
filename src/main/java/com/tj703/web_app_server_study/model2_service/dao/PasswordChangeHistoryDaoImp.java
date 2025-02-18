package com.tj703.web_app_server_study.model2_service.dao;

import com.tj703.web_app_server_study.model2_service.dto.PasswordChangeHistoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PasswordChangeHistoryDaoImp implements PasswordChangeHistoryDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public PasswordChangeHistoryDaoImp(Connection conn) {
        this.conn = conn;
    }


    @Override
    public int insert(PasswordChangeHistoryDto dto) throws Exception {
        int result=0;
        String sql="INSERT INTO password_change_history (user_id, old_password) values (?,?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, dto.getUserId());
        ps.setString(2, dto.getOldPassword());
        result=ps.executeUpdate();
        return result;
    }

    @Override
    public List<PasswordChangeHistoryDto> findByChangeAtAndUserId(String changeAt, int userId) throws Exception {
        List<PasswordChangeHistoryDto> list=null;
        String sql="SELECT * FROM password_change_history WHERE user_id=? AND changed_at<?";
        ps=conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, changeAt);
        rs=ps.executeQuery();
        list=new ArrayList<>();
        while(rs.next()){
            PasswordChangeHistoryDto dto=new PasswordChangeHistoryDto();
            dto.setUserId(rs.getInt("user_id"));
            dto.setOldPassword(rs.getString("old_password"));
            dto.setChangedAt(rs.getString("changed_at"));
            dto.setChangeId(rs.getInt("change_id"));
            list.add(dto);
        }
        return list;    }

    @Override
    public List<PasswordChangeHistoryDto> findByPwAndUserId(String pw, int userId) throws Exception {
        List<PasswordChangeHistoryDto> list=null;
        String sql="SELECT * FROM password_change_history WHERE user_id=? AND old_password=?";
        ps=conn.prepareStatement(sql);
        ps.setInt(1, userId);
        ps.setString(2, pw);
        rs=ps.executeQuery();
        list=new ArrayList<>();
        while(rs.next()){
            PasswordChangeHistoryDto dto=new PasswordChangeHistoryDto();
            dto.setUserId(rs.getInt("user_id"));
            dto.setOldPassword(rs.getString("old_password"));
            dto.setChangedAt(rs.getString("changed_at"));
            dto.setChangeId(rs.getInt("change_id"));
            list.add(dto);
        }
        return list;
    }
}
