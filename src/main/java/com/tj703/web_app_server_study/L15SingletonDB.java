package com.tj703.web_app_server_study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class L15SingletonDB {
    //디자인패턴 : 코드 구성만으로 기능을 구현
    //싱글톤패턴 : 객체를 만들고 재사용 하는 목적으로 구성하는 디자인
    private static Connection conn;
    private static final String URL="jdbc:mysql://localhost:3306/employees";
    private static final String USER="root";
    private static final String PASSWORD="mysqlmysql";
    private static final String DRIVER="com.mysql.cj.jdbc.Driver";
    static { //컴파일시 static{}을 함수처럼 호출
        try {
            Class.forName(DRIVER);
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
            //컴파일시 실행한다는 것은 오류가 발생하면 어플 전체에 문제가 된다는 것
            //->꼭 오류로 어플을 종료시켜야한다.
        }
    }

    public static Connection getConnection() throws Exception {
        if(conn == null || conn.isClosed()) {
            Class.forName(DRIVER);
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
        }
        return conn;
    }
    //1.필드를 접근할 수 없게 막고 함수로만 반환시킴
    //2.해당 필드에 객체가 존재하면 그냥 반환 (새로 만들지 않고 기존 객체를 반환)
    //3.해당 필드가 존재하지 않거나 사용할 수 없는 상태면 객체를 생성해서 대입 후 반환
}
