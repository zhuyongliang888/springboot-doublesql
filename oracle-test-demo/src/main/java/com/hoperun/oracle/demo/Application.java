package com.hoperun.oracle.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.testdemo.commom.utils.PrintTest;


// @EnableTransactionManagement //开启事务管理
//@EnableScheduling
@SpringBootApplication
//@MapperScan(basePackages= {"com.hoperun"})
public class Application extends SpringBootServletInitializer
{

    public static void main(String[] args)
    {
	System.out.println("============== SpringApplication.run start===========");
	SpringApplication.run(Application.class, args);
	System.out.println("============== SpringApplication.run end=============");
	PrintTest.testPrint();
    }
}
