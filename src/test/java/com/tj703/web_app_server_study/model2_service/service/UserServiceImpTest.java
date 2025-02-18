package com.tj703.web_app_server_study.model2_service.service;

import com.tj703.web_app_server_study.model2_service.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImpTest {

    @Test
    void login() throws Exception {
        System.out.println(new UserServiceImp().login("user2@example.com","1234"));
    }

    @Test
    void signup() throws Exception {
        UserDto user = new UserDto();
        user.setEmail("signupTestLast@example.com");
        user.setPassword("5555");
        System.out.println(new UserServiceImp().signup(user));
    }

    @Test
    void modifyPw() throws Exception {
        UserDto user = new UserDto();
        user.setEmail("signupTestLast@example.com");
        user.setPassword("비밀번호변경");
        System.out.println(new UserServiceImp().modifyPw(user));

    }

}