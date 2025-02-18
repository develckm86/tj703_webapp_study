package com.tj703.web_app_server_study.model2_service.service;

import com.tj703.web_app_server_study.model2_service.dao.*;
import com.tj703.web_app_server_study.model2_service.dto.LoginLogDto;
import com.tj703.web_app_server_study.model2_service.dto.PasswordChangeHistoryDto;
import com.tj703.web_app_server_study.model2_service.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImp implements UserService {
    //@AutoWired
    private Connection conn;
    private UserDao userDao;
    private LoginLogDao loginLogDao;
    private PasswordChangeHistoryDao pwHistoryDao;
    //객체지향언어(java)의 단점
    // 순서대로 동작  -> 객체를 매개변수로 사용 -> 서비스로직이 더 복잡해진다.
    // 객체를 필요로 하는 곳에 항상 생성 ->
    // 서버환경에서 10000개의 동일한 요청이 들어오면 같은 요청이더라도 10000개의 객체를 생성

    //-> 관점지향 언어로(Spring(base java))
    // 객체를 컨테이너(==singleton pattern)가 따로 생성하고 사용하는 곳에 전달하는 구조
    // 1. 매개변수 없이 동작
    // 2. 만들어진 것이 있으면 같은 요청이 왔을 때 전달
    // 3. 무엇이든 서비스 로직을 기준으로 개발이 가능(개발이 편해진다.)
    // 서버환경에서 10000개의 동일한 요청이 들어오면 1개의 객체로 처리

    public UserServiceImp() throws Exception {
        conn= UserManagerDBConn.getConnection();
        userDao=new UserDaoImp(conn);
        loginLogDao=new LoginLogDaoImp(conn);
        pwHistoryDao=new PasswordChangeHistoryDaoImp(conn);
    }
    //@Transataional
    @Override
    public Map<String, Object> login(String email, String pw) throws Exception {
        try {
            conn.setAutoCommit(false); //쿼리를 실행할때마다 각 쿼리가 독립성을 갖기 때문
            conn.commit(); //save Point
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
        } catch (Exception e) {
            conn.rollback();
            //중간에 오류가 발생하면 commit 지점으로 되돌림 -> 원자성을 유지==Transaction
            throw new RuntimeException(e); //사용하는 쪽에서 오류를 발생
        }finally {
            if(conn!=null){conn.close();}
        }
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
