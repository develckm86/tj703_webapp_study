package com.tj703.web_app_server_study.model2_service.dao;

import com.tj703.web_app_server_study.model2_service.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImpTest {

    @Test
    void findByUserIdAndPassword() throws Exception {
        Connection conn=UserManagerDBConn.getConnection();
        System.out.println(new UserDaoImp(conn).findByUserIdAndPassword(1,"1234"));
    }

    @Test
    void insert() throws Exception {
        Connection conn=UserManagerDBConn.getConnection();

        UserDto user = new UserDto();
        user.setPassword("1234");
        user.setEmail("insertTest@gmail.com");
        System.out.println(new UserDaoImp(conn).insert(user));

    }

    @Test
    void findByEmailAndPassword() throws Exception {
        Connection conn=UserManagerDBConn.getConnection();
        System.out.println(new UserDaoImp(conn).findByEmailAndPassword("insertTest@gmail.com","1234"));
    }

    @Test
    void updateSetPwByEmail() throws Exception {
        Connection conn=UserManagerDBConn.getConnection();
        UserDto user = new UserDto();
        user.setPassword("9999");
        user.setEmail("user1@example.com");
        System.out.println(new UserDaoImp(conn).updateSetPwByEmail(user));
    }
}