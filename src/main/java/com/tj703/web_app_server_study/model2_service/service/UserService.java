package com.tj703.web_app_server_study.model2_service.service;

import com.tj703.web_app_server_study.model2_service.dto.LoginLogDto;
import com.tj703.web_app_server_study.model2_service.dto.UserDto;
import com.tj703.web_app_server_study.model2_service.dto.UserServiceLoginDto;

import java.util.Map;

public interface UserService {
    //평문 비밀번호
    UserServiceLoginDto login (UserDto user, LoginLogDto loginLog) throws Exception;
    //해시코드 비밀번호
    UserServiceLoginDto AutoLogin (UserDto user, LoginLogDto loginLog) throws Exception;

    Map<String,Object> login (String email, String pw, String ip, String agent) throws Exception;
    boolean signup(UserDto user) throws Exception;
    boolean modifyPw(UserDto user) throws Exception;

    //서비스의 함수 이름은 쿼리와는 상관없는 서비스 이름으로 작명
    //dao findAll == Select, insert == insert findById : sql과 가까운 작명
    //login : 1. db에 저장된 유저 정보와 email pw 가 동일하것 이 있는지 조회
    //2. login_log 저장
    //3. pw_history 조회(6개월전) ->true 패스워드 변경 페이지, false 그냥 로그인
    //보내야할 데이터가 복수일때 데이터를 보내는 방법
    //1. list(x), Map(*) userDto:UserDto, isPwHistory:Boolean
    //2. UserDto 에 boolean isPwHistory 필드를 정의 (권장하지 않음)
    //3. UserVo(Beans) 를 따로 만들어서 UserDto 필드 + isPwHistory 정의
    //회원가입
    //1. 회원 등록
    //2. 회원 패스워드 수정 이력 등록 -> error 를 로그로 남김
    // => boolean
    //enum SignupStatus{ SUCCESS, PREV_PW, PW_HISTORY_ERROR  }
    //회원 비번 수정
    //0. 이전에 동일한 패스워드 사용한 이력이 있는지 검사
    //1. 회원의 비번 수정
    //2. 회원 패스워드 수정 이력 등록
    //개발순서
    //0.dto 정의
    //1.서비스 기획 -> 서비스 인터페이스
    //2.서비스에 필요한 dao 기획
    //3.dao 구현 및 테스트
    //4.서비스 구현

}
