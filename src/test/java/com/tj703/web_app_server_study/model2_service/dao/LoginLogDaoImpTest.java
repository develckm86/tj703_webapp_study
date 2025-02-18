package com.tj703.web_app_server_study.model2_service.dao;

import com.tj703.web_app_server_study.model2_service.dto.LoginLogDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class LoginLogDaoImpTest {
    private static Connection conn;
    //@BeforeAll //모든 함수 이전에 시작 1번 실행 (static) <->AfterAll
    @BeforeEach //함수 마다 이전에 실행 <-> AfterEach
    void setUpBeforeClass() throws Exception {
        conn=UserManagerDBConn.getConnection();
    }

    @Test
    void insert() throws Exception {
        LoginLogDto dto=new LoginLogDto();
        dto.setUserId(1);
        dto.setIpAddress("127.0.0.1");
        dto.setUserAgent("Mozilla/5.0");
        System.out.println(new LoginLogDaoImp(conn).insert(dto));
    }

    @Test
    void findAll() throws Exception{
        System.out.println(new LoginLogDaoImp(conn).findAll());
    }
}