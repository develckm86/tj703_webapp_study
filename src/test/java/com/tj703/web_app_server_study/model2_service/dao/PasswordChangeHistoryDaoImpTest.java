package com.tj703.web_app_server_study.model2_service.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class PasswordChangeHistoryDaoImpTest {
    private static Connection conn;
    private static PasswordChangeHistoryDao dao;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        conn=UserManagerDBConn.getConnection();
        dao=new PasswordChangeHistoryDaoImp(conn);
    }
    @Test
    void insert() {
    }

    @Test
    void findByChangeAt() {
    }

    @Test
    void findByPwAndUserId() throws Exception{
        System.out.println(dao.findByPwAndUserId("1234",1));

    }
}