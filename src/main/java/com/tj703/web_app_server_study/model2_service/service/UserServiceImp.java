package com.tj703.web_app_server_study.model2_service.service;

import com.tj703.web_app_server_study.model2_service.dao.*;
import com.tj703.web_app_server_study.model2_service.dto.LoginLogDto;
import com.tj703.web_app_server_study.model2_service.dto.PasswordChangeHistoryDto;
import com.tj703.web_app_server_study.model2_service.dto.UserDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImp implements UserService {
    private Connection conn;
    private UserDao userDao;
    private LoginLogDao loginLogDao;
    private PasswordChangeHistoryDao pwHistoryDao;
    public UserServiceImp() throws Exception {
        conn= UserManagerDBConn.getConnection();
        userDao=new UserDaoImp(conn);
        loginLogDao=new LoginLogDaoImp(conn);
        pwHistoryDao=new PasswordChangeHistoryDaoImp(conn);
    }

    @Override
    public Map<String, Object> login(String email, String pw) throws Exception {
        Map<String, Object> login=new HashMap<>();
        UserDto user=userDao.findByEmailAndPassword(email, pw);
        LoginLogDto loginLog=new LoginLogDto();
        loginLog.setUserId(user.getUserId());
        loginLog.setIpAddress("127.0.0.1");
        loginLog.setUserAgent("Mozilla");
        int insert=loginLogDao.insert(loginLog);

        LocalDate now=LocalDate.now();
        String prevSixMonth=now.minusMonths(6).toString();
        //2024-08-18
        List<PasswordChangeHistoryDto> pwList=
                pwHistoryDao.findByChangeAtAndUserId(prevSixMonth,user.getUserId());
        login.put("userDto",user);
        login.put("isPwHistory",pwList.size()>0);
        login.put("isInsertLog",insert>0);
        return login;
    }

    @Override
    public boolean signup(UserDto user) throws Exception {
        return false;
    }

    @Override
    public boolean modifyPw(UserDto user) throws Exception {
        return false;
    }
}
