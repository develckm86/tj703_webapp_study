package com.tj703.web_app_server_study.model2_service.dao;

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
    void insert() {
    }

    @Test
    void findByEmailAndPassword() throws Exception {
        Connection conn=UserManagerDBConn.getConnection();
        assertNotNull(new UserDaoImp(conn).findByEmailAndPassword("user1@example.com","1234"));
    }
}