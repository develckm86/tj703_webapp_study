package com.tj703.web_app_server_study.model2_service.service;

import com.tj703.web_app_server_study.model2_service.dao.*;
import com.tj703.web_app_server_study.model2_service.dto.LoginLogDto;
import com.tj703.web_app_server_study.model2_service.dto.PasswordChangeHistoryDto;
import com.tj703.web_app_server_study.model2_service.dto.UserDto;
import com.tj703.web_app_server_study.model2_service.dto.UserServiceLoginDto;
import org.mindrot.jbcrypt.BCrypt;

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
    // 서버환경에서 10000개의 동일한 요청이 들어오면 같은 요청이더라도 최소 10000개의 객체를 생성

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

    @Override
    public UserServiceLoginDto login(UserDto user, LoginLogDto loginLog) throws Exception {
        UserServiceLoginDto login=null;
        try {
            conn.setAutoCommit(false);
            conn.commit();
            UserDto loginUser=userDao.findByEmail(user.getEmail());

            if ( loginUser==null || !BCrypt.checkpw(user.getPassword(), loginUser.getPassword())) {return login;}

            loginLog.setUserId(loginUser.getUserId());
            int loginInsert=loginLogDao.insert(loginLog);
            List<PasswordChangeHistoryDto> pwHistoryList=null;
            LocalDate now=LocalDate.now();
            String prevSixMonth=now.minusMonths(6).toString();
            pwHistoryList=pwHistoryDao.findByChangeAtAndUserId( prevSixMonth,loginUser.getUserId());
            login=new UserServiceLoginDto();
            login.setUser(loginUser);
            login.setPwHistory(pwHistoryList.size()>0);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw new RuntimeException(e);
        }finally {
            if(conn!=null){conn.close();}
        }
        return login;
    }

    @Override
    public UserServiceLoginDto AutoLogin(UserDto user, LoginLogDto loginLog) throws Exception {
        return null;
    }

    //@Transataional
    @Override
    public Map<String, Object> login(String email, String pw, String ip, String agent) throws Exception {
        Map<String, Object> login=new HashMap<>();
        try {
            conn.setAutoCommit(false); //쿼리를 실행할때마다 각 쿼리가 독립성을 갖기 때문
            conn.commit(); //save Point
            UserDto user=userDao.findByEmail(email);


           // if(user==null){return login;}
            /*BCrypt 암호화가 되어 있는 경우  == user.getPw()가 해시코드다 */
            //pw="1234" 평문
            System.out.println(pw);
            System.out.println(user);

            if( user==null || !BCrypt.checkpw(pw,user.getPassword()) ){return login;}

            //로그인을 실패하면 로그를 남기거나 히스토리를 조회하지 않는다.
            LoginLogDto loginLog=new LoginLogDto();
            loginLog.setUserId(user.getUserId());
            loginLog.setIpAddress(ip);
            loginLog.setUserAgent(agent);
            int insert=loginLogDao.insert(loginLog);

            LocalDate now=LocalDate.now();
            String prevSixMonth=now.minusMonths(6).toString();
            //2024-08-20
            //2025-05-20
            List<PasswordChangeHistoryDto> pwList=
                    pwHistoryDao.findByChangeAtAndUserId(prevSixMonth,user.getUserId());
            login.put("userDto",user);
            login.put("isPwHistory",pwList.size()>0);
            login.put("isInsertLog",insert>0);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            //중간에 오류가 발생하면 commit 지점으로 되돌림 -> 원자성을 유지==Transaction
            throw new RuntimeException(e); //사용하는 쪽에서 오류를 발생
        }finally {
            if(conn!=null){conn.close();}
        }
        return login;

    }

    @Override
    public boolean signup(UserDto user) throws Exception {
        boolean signup=false;
        try {
            conn.setAutoCommit(false);
            conn.commit();
            ///signup(UserDto user) user={email:"test",pw:"1234" userId:xx}
            int insert=userDao.insert(user);
            //1.Select last_insert_id() 조회 (auto Increment로 생성된 아이디 조회)
            //2. 그냥 등록된 유저 조회
            user=userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
            PasswordChangeHistoryDto pwHistoryDto=new PasswordChangeHistoryDto();
            pwHistoryDto.setUserId(user.getUserId());
            pwHistoryDto.setOldPassword(user.getPassword());
            int pwInsert= pwHistoryDao.insert(pwHistoryDto);
            signup=(pwInsert>0 && insert>0);
            conn.commit(); //등록한 내역이 휘발됨
        } catch (Exception e) {
            conn.rollback();
            throw new RuntimeException(e);
        }finally {
            if(conn!=null){conn.close();}
        }
        return signup;
    }

    @Override
    public boolean modifyPw(UserDto user) throws Exception {
        boolean modifyPw=false;
        try {
            conn.setAutoCommit(false);
            conn.commit();
            //이전에 유저가 바꾸려는 비밀번호와 동일한 비번을 사용한 이력이 있나

            List<PasswordChangeHistoryDto> pwList=pwHistoryDao.findByPwAndEmail( user.getPassword(), user.getEmail());
            if(pwList.size()>0){ return modifyPw; }

            int userInsert=userDao.updateSetPwByEmail(user);
            user=userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
            PasswordChangeHistoryDto pwHistoryDto=new PasswordChangeHistoryDto();
            pwHistoryDto.setUserId(user.getUserId());
            pwHistoryDto.setOldPassword(user.getPassword());
            int pwInsert= pwHistoryDao.insert(pwHistoryDto);
            modifyPw=(pwInsert>0 && userInsert>0);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw new RuntimeException(e);
        }finally {
            if(conn!=null){conn.close();}
        }
        return modifyPw;
    }
}
