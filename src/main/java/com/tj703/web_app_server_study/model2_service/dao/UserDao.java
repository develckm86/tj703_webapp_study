package com.tj703.web_app_server_study.model2_service.dao;

import com.tj703.web_app_server_study.model2_service.dto.UserDto;

public interface UserDao {
    //UserDto findByIdAndPw(String id, String pw);
    UserDto findByUserIdAndPassword(int id, String pw) throws Exception;
    UserDto findByEmailAndPassword(String email, String pw) throws Exception;
    UserDto findByEmail(String email) throws Exception;

    int insert(UserDto user) throws Exception;
    int updateSetPwByEmail(UserDto user) throws Exception;
    //서비스웹앱
    //로그인 id,pw
    //회원가입

    //관리자웹앱
    //유저전체조회
    //상세조회
    //수정
    //삭제
}
