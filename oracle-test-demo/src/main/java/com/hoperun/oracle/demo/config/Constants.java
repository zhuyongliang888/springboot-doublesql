package com.hoperun.oracle.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class Constants
{
    static {
	System.out.println("正在执行静态初始化块");
    }
    {
	System.out.println("正在执行非静态初始化块");
    }
    
    public Constants() {
	System.out.println("正在执行构造方法");
    }
    @Value("${define.name.value}")
    public String name;
    
    
    //static 无法通过属性注入的方式进入Name
//    public static String Name;
//    @Value("${define.name.value}")
//    public void setName(String name) {
//	Name =name;
//	testName();
//    }
//    private void testName() {
//	System.out.println("正在使用setName进行赋值");
//    }
}
