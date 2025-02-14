package com.tj703.web_app_server_study.model2_service.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserManagerDBConn {
    private static final String URL="jdbc:mysql://localhost:3306/UserManagement";
    private static final String USER="usermanager";
    private static final String PASSWORD="mysql";
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";

    private static Connection conn;
    static{
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws Exception{
        if(conn==null || conn.isClosed()) {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        }
        return conn;
    }
}
