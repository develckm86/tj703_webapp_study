package com.tj703.web_app_server_study;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
//model1: servlet 내부에 service+view
//model2: servlet(Controller)과 service(Model)와 view가 분리된 디자인패턴 (MVC)
class L16EmpDaoImpTest {
    @Test
    void findAll() {
    }

    @Test
    void findById() throws Exception {
        System.out.println(new L16EmpDaoImp().findById(10001));
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}