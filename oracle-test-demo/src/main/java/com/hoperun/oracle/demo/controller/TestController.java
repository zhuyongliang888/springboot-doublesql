package com.hoperun.oracle.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hoperun.oracle.demo.common.PortalUtils;
import com.hoperun.oracle.demo.facade.UserService;
import com.hoperun.oracle.demo.model.Thistory;
import com.hoperun.oracle.demo.model.UUID;
import com.hoperun.oracle.demo.model.User;


@RestController
public class TestController
{
    @Autowired
    private UserService userService;
    
    
    @RequestMapping("/hello")
    private String hello()
    {
	return "success";
    }
    
    @RequestMapping(value = "/query", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String getUserListInfo(HttpServletRequest request, HttpServletResponse response)
    {
	PortalUtils.setResponseAttribute(response);
	List<User> dbValues = userService.getUserListInfo();
	String result = JSON.toJSONString(dbValues, SerializerFeature.WriteMapNullValue);
	response.setStatus(200);
	return result;
    }

    @RequestMapping(value = "/query2", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public String getThisrotyListInfo(HttpServletRequest request, HttpServletResponse response)
    {
	PortalUtils.setResponseAttribute(response);
	List<Thistory> dbValues = userService.getThistory();
	String result = JSON.toJSONString(dbValues, SerializerFeature.WriteMapNullValue);
	response.setStatus(200);
	return result;
    }
    
    @RequestMapping(value = "/insert/user", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String addUser(@RequestBody User userRequest,  HttpServletRequest request, HttpServletResponse response)
    {
	PortalUtils.setResponseAttribute(response);
	List<User> resultList = userService.addUser(userRequest);
	String result = JSON.toJSONString(resultList, SerializerFeature.WriteMapNullValue);
	response.setStatus(200);
	return result;
    }
    
    @RequestMapping(value = "/delete/user", produces = "application/json;charset=UTF-8", method = RequestMethod.DELETE)
    public String removeUser(@RequestBody User userRequest,  HttpServletRequest request, HttpServletResponse response)
    {
	PortalUtils.setResponseAttribute(response);
	List<User> resultList = userService.deleteUser(userRequest);
	String result = JSON.toJSONString(resultList, SerializerFeature.WriteMapNullValue);
	response.setStatus(200);
	return result;
    }
    
    @RequestMapping(value = "/insert/testuuid", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String addUUID(@RequestBody UUID uuidRequest,  HttpServletRequest request, HttpServletResponse response)
    {
	PortalUtils.setResponseAttribute(response);
	List<UUID> resultList = userService.addUUID(uuidRequest);
	String result = JSON.toJSONString(resultList, SerializerFeature.WriteMapNullValue);
	response.setStatus(200);
	return result;
    }
    
}
