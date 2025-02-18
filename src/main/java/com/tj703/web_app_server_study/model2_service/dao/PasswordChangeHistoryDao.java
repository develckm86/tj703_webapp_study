package com.tj703.web_app_server_study.model2_service.dao;

import com.tj703.web_app_server_study.model2_service.dto.PasswordChangeHistoryDto;

import java.util.List;

public interface PasswordChangeHistoryDao {
    int insert(PasswordChangeHistoryDto dto) throws Exception;
    //changeAt : 오늘날짜 - 6개월 전
    List<PasswordChangeHistoryDto> findByChangeAtAndUserId(String changeAt,int userId) throws Exception;
    //지금 수정하려는 패스워드가 전에 사용한 이력이 있나?
    List<PasswordChangeHistoryDto> findByPwAndUserId(String pw, int userId) throws Exception;

}
