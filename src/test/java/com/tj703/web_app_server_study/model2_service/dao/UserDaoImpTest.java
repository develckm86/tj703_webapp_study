package com.tj703.web_app_server_study.model2_service.dao;

import com.tj703.web_app_server_study.model2_service.dto.UserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

class UserDaoImpTest {
    private static Connection conn;
    private static UserDao userDao;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        conn=UserManagerDBConn.getConnection();
        userDao=new UserDaoImp(conn);
    }
    @Test
    void findByUserIdAndPassword() throws Exception {
        System.out.println(userDao.findByUserIdAndPassword(1,"1234"));
    }

    @Test
    void insert() throws Exception {

        UserDto user = new UserDto();
        user.setPassword("1234");
        user.setEmail("insertTest2@gmail.com");
        System.out.println(userDao.insert(user));

    }

    @Test
    void findByEmailAndPassword() throws Exception {
        System.out.println(userDao.findByEmailAndPassword("insertTest2@gmail.com","5555"));
    }

    @Test
    void updateSetPwByEmail() throws Exception {
        UserDto user = new UserDto();
        user.setPassword("5555");
        user.setEmail("insertTest2@gmail.com");
        System.out.println(userDao.updateSetPwByEmail(user));
    }

    @Test
    void findByEmail() throws Exception {
        System.out.println(userDao.findByEmail("user1@example.com"));
    }
}