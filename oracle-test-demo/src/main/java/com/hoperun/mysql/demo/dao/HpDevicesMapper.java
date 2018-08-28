package com.hoperun.mysql.demo.dao;

import java.util.List;

import com.hoperun.mysql.demo.model.HpDevices;

public interface HpDevicesMapper {
    int deleteByPrimaryKey(String devId);

    int insert(HpDevices record);

    int insertSelective(HpDevices record);

    HpDevices selectByPrimaryKey(String devId);

    int updateByPrimaryKeySelective(HpDevices record);

    int updateByPrimaryKey(HpDevices record);

    List<HpDevices> getDeviceList();
}