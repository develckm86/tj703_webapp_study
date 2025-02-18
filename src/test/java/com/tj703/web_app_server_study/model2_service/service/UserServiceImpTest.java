package com.tj703.web_app_server_study.model2_service.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImpTest {

    @Test
    void login() throws Exception {
        System.out.println(new UserServiceImp().login("user2@example.com","1234"));
    }

    @Test
    void signup() {
    }

    @Test
    void modifyPw() {
    }
}