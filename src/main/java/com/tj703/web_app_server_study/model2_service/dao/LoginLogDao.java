package com.tj703.web_app_server_study.model2_service.dao;

import com.tj703.web_app_server_study.model2_service.dto.LoginLogDto;

import java.util.List;

public interface LoginLogDao {
    int insert(LoginLogDto dto) throws Exception;//login 할때 마다 등록
    List<LoginLogDto> findAll() throws Exception;//관리자가 유저가 로그인한 로그를 본다
}
