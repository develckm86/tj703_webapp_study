package com.tj703.web_app_server_study.model2.dao;
import com.tj703.web_app_server_study.model2.dto.L17EmpDto;

import java.util.*;
public interface L16EmpDao {
    /*리스트,상세,(수정,삭제,등록)->int 설계==추상메서드*/
    //find :select, by : where, id :pw
    List<L17EmpDto> findAll() throws Exception;
    L17EmpDto findById(int empNo) throws Exception;
    int insert(L17EmpDto emp) throws Exception;
    int update(L17EmpDto emp) throws Exception;
    int deleteById(int empNo) throws Exception;
}
