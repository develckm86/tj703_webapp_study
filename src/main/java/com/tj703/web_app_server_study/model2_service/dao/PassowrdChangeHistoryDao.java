package com.tj703.web_app_server_study.model2_service.dao;

import com.tj703.web_app_server_study.model2_service.dto.PasswordChangeHistoryDto;

import java.util.List;

public interface PassowrdChangeHistoryDao {
    int insert(PasswordChangeHistoryDto dto);
    //changeAt : 오늘날짜 - 6개월 전
    List<PasswordChangeHistoryDto> findByChangeAt(String changeAt);
    //지금 수정하려는 패스워드가 전에 사용한 이력이 있나?
    List<PasswordChangeHistoryDto> findByPwAndUserId(String pw, Long userId);

}
