package com.hoperun.oracle.demo.dao;

import java.util.List;

import com.hoperun.oracle.demo.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Short idp);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Short idp);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> getUserList();
}