package com.hoperun.oracle.demo;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Component 将其类加上标注@Component 后此类将被以实列的方式注入到spring 上下文中 
@Configuration
@PropertySource(value = "classpath:/p.properties")
@ConfigurationProperties(prefix = "com.demo.test")
public class People
{
    private String name;
    private int age;
    private Phone phone;
    private List<String> address;
    
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public Phone getPhone()
    {
        return phone;
    }
    public void setPhone(Phone phone)
    {
        this.phone = phone;
    }
    public List<String> getAddress()
    {
	return address;
    }
    public void setAddress(List<String> address)
    {
	this.address = address;
    }
    
    //@Bean 存在的地方必须是标注了@Configuration的类中
    @Bean
    public People getPeople() {
	    return new People();
	}
    
}




