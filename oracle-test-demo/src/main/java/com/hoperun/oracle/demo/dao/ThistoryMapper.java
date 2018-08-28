package com.hoperun.oracle.demo.dao;

import java.util.List;

import com.hoperun.oracle.demo.model.Thistory;

public interface ThistoryMapper {
    int deleteByPrimaryKey(Short expect);

    int insert(Thistory record);

    int insertSelective(Thistory record);

    Thistory selectByPrimaryKey(Short expect);

    int updateByPrimaryKeySelective(Thistory record);

    int updateByPrimaryKey(Thistory record);
    
    List<Thistory> getThistoryList();
}