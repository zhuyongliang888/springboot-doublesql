package com.hoperun.oracle.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hoperun.mysql.demo.model.HpDevices;
import com.hoperun.oracle.demo.common.PortalUtils;
import com.hoperun.oracle.demo.facade.UserService;

@RestController
public class TestMysqlController
{

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/mysql/query", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String getUserListInfo(HttpServletRequest request, HttpServletResponse response)
    {
	PortalUtils.setResponseAttribute(response);
	List<HpDevices> dbValues = userService.getDeviceListInfo();
	String result = JSON.toJSONString(dbValues, SerializerFeature.WriteMapNullValue);
	response.setStatus(200);
	return result;
    }
}
