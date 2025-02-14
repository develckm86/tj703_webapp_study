package com.tj703.web_app_server_study.model2_service.dao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerDBConnTest {

    @Test
    void getConnection() throws Exception {
        assertNotNull(UserManagerDBConn.getConnection());
    }
}