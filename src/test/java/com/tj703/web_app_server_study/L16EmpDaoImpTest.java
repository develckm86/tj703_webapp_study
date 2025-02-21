package com.tj703.web_app_server_study;

import com.tj703.web_app_server_study.model2.dao.L16EmpDao;
import com.tj703.web_app_server_study.model2.dao.L16EmpDaoImp;
import com.tj703.web_app_server_study.model2.dto.L17EmpDto;
import org.junit.jupiter.api.*;
import org.mindrot.jbcrypt.BCrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

//model1: servlet 내부에 service+view
//model2: servlet(Controller)과 service(Model)와 view가 분리된 디자인패턴 (MVC)
//junit 에서 class 에 실행을 누르면 내부에 정의된 테스트가 동시에 실행되기 때문에 (멀티스레드)
//@TestMethodOrder @Order 로 순서를 정할 수 있다.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class L16EmpDaoImpTest {
    @Test
    @Order(4)
    void findAll() throws Exception {
        System.out.println(new L16EmpDaoImp().findAll());
    }

    @Test
    @Order(2)
    void findById() throws Exception {
        System.out.println(new L16EmpDaoImp().findById(9999));
    }

    @Test
    @Order(1)
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
    @Order(3)
    void update() throws Exception {
        L17EmpDto emp=new L17EmpDto();
        emp.setEmpNo(9999);
        emp.setBirthDate("1988-1-27");
        emp.setGender("F");
        emp.setFirstName("준");
        emp.setLastName("어눌도");
        emp.setHireDate("2024-3-10");
        int update=new L16EmpDaoImp().update(emp);
        assertTrue(update > 0);
    }

    @Test
    @Order(5)
    void deleteById() throws Exception {
        int delete=new L16EmpDaoImp().deleteById(9999);
        assertTrue(delete > 0);
    }
}