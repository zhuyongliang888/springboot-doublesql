package com.hoperun.oracle.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@EnableConfigurationProperties(People.class)
public class ConfigurationPropertiesTest
{
    @Autowired
    private People people;
    
    @GetMapping("/getName")
    public String getName() {
	return people.getName();
    }
    @GetMapping("/getAge")
    public int getAge() {
	return people.getAge();
    }
    @GetMapping("/getPhoneNumber")
    public String getPhoneNumber() {
	return people.getPhone().getPhonenumber();
    }
    
    @GetMapping("/getAddressList")
    public String getList() {
	return people.getAddress().toString();
    }
    
}
