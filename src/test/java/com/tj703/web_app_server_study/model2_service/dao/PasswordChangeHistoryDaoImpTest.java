package com.tj703.web_app_server_study.model2_service.dao;

import com.tj703.web_app_server_study.model2_service.dto.PasswordChangeHistoryDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class PasswordChangeHistoryDaoImpTest {
    private static Connection conn;
    private static PasswordChangeHistoryDao dao;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        conn=UserManagerDBConn.getConnection();
        dao=new PasswordChangeHistoryDaoImp(conn);
    }
    @Test
    void insert() throws Exception {
        PasswordChangeHistoryDto dto=new PasswordChangeHistoryDto();
        dto.setUserId(3);
        dto.setOldPassword("1234");
        System.out.println(dao.insert(dto));

    }

    @Test
    void findByChangeAtAndUserId() throws Exception {
        //java.util.Date 타입과 문자열과 수를 Date 로 파싱 => LocalDate
        Date date=new Date();
        System.out.println(date);//Tue Feb 18 12:43:30 KST 2025
        //2025-2-18
        int y=date.getYear()+1900;
        int m=date.getMonth()+1; //0~11
        int d=date.getDate();
        System.out.println(y+"-"+m+"-"+d);
        //SimpleDateFormat : 문자열 -> Date or Date -> 문자열
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");
        System.out.println(sdf.format(date));

        Date date1=new Date(0); //밀리초가 0인 날짜
        System.out.println(sdf.format(date1));
        System.out.println(date.getTime());//1739850620583
        // 1*1000*60*60*24*30*6
        long sixM=1l * 1000l * 60l * 60l * 24l * 31l * 6l;
        System.out.println(date.getTime()-sixM);
        System.out.println(sdf.format(new Date(date.getTime()-sixM)));

        //Calendar Date 를 달력 계산해주는 클래스
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, -6);
        System.out.println(calendar.getTime());
        //java.time.LocalDate
        LocalDateTime now=LocalDateTime.now(); //mysql date
        System.out.println(now);//2025-02-18
        System.out.println(now.minusMonths(6));
        System.out.println(now.toEpochSecond(ZoneOffset.UTC));//1739884576
        //LocalDateTime == mysql datetime
        //자바개발자 특징 보수적
        String prevSixMonth=now.minusMonths(6).toString();

        System.out.println(dao.findByChangeAtAndUserId(prevSixMonth,1));
    }

    @Test
    void findByPwAndUserId() throws Exception{
        System.out.println(dao.findByPwAndUserId("1234",3));

    }
}