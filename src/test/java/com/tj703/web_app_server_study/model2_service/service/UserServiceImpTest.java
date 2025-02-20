package com.tj703.web_app_server_study.model2_service.service;

import com.tj703.web_app_server_study.model2_service.dto.LoginLogDto;
import com.tj703.web_app_server_study.model2_service.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImpTest {

    @Test
    void login() throws Exception {
        System.out.println(new UserServiceImp().login("user2@example.com","1234","",""));
    }

    @Test
    void signup() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("servieSignup@example.com");
        userDto.setPassword("4321");
        System.out.println(new UserServiceImp().signup(userDto));
    }

    @Test
    void modifyPw() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("servieSignup@example.com");
        userDto.setPassword("5555");
        System.out.println(new UserServiceImp().modifyPw(userDto));

    }

    @Test
    void testLogin() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("user2@example.com");
        userDto.setPassword("5555");
        LoginLogDto loginLogDto=new LoginLogDto();
        loginLogDto.setUserAgent("chrome");
        loginLogDto.setIpAddress("localhost");
        System.out.println(new UserServiceImp().login(userDto,loginLogDto));
    }
}