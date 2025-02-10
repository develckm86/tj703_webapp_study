package com.tj703.web_app_server_study;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class L15SingletonDBTest {
    //함수단위로 실행할 수 있어서 단위 테스트라 부른다. unit test
    //java unit test == junit
    //단위 테스트를 하면서 개발 == TDD(테스트 주도 개발)
    @Test
    void getConnection() throws Exception { //default 오류-> 실패
        L15SingletonDB.getConnection();
    }
}