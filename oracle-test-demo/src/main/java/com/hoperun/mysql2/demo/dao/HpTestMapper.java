package com.hoperun.mysql2.demo.dao;

import java.util.List;

import com.hoperun.mysql2.demo.model.HpTest;

public interface HpTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HpTest record);

    int insertSelective(HpTest record);

    HpTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HpTest record);

    int updateByPrimaryKey(HpTest record);
    
    List<HpTest> getListInfo();
}