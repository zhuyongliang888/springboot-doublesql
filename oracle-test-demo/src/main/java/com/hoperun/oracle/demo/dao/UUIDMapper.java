package com.hoperun.oracle.demo.dao;

import java.util.List;

import com.hoperun.oracle.demo.model.UUID;

public interface UUIDMapper {
    int deleteByPrimaryKey(String id);

    int insert(UUID record);

    int insertSelective(UUID record);

    UUID selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UUID record);

    int updateByPrimaryKey(UUID record);

    List<UUID> getUUIDList();
}