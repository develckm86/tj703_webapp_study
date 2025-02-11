package com.tj703.web_app_server_study;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

//model1: servlet 내부에 service+view
//model2: servlet(Controller)과 service(Model)와 view가 분리된 디자인패턴 (MVC)
class L16EmpDaoImpTest {
    @Test
    void findAll() throws Exception {
        System.out.println(new L16EmpDaoImp().findAll());
    }

    @Test
    void findById() throws Exception {
        System.out.println(new L16EmpDaoImp().findById(9999));
    }

    @Test
    void insert() throws Exception {
        L17EmpDto emp=new L17EmpDto();
        emp.setEmpNo(9999);
        emp.setBirthDate("1999-12-31");
        emp.setGender("M");
        emp.setFirstName("존");
        emp.setLastName("아놀드");
        emp.setHireDate("2025-2-11");
        int insert=new L16EmpDaoImp().insert(emp);
        System.out.println(insert);
        //0보다 크지 않으면 실패 : assertTrue 내부 결과가 true 일때면 성공
        assertTrue(insert > 0);
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}