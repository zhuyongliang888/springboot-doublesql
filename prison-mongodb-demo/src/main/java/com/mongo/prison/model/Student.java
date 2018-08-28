package com.mongo.prison.model;

import org.springframework.data.annotation.Id;

public class Student
{
    @Id
    private String id;
    private String name;
    private String sex;
    private Integer age;
    private String des;
    private StudentScore studentScore;
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getSex()
    {
        return sex;
    }
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    public Integer getAge()
    {
        return age;
    }
    public void setAge(Integer age)
    {
        this.age = age;
    }
    public String getDes()
    {
        return des;
    }
    public void setDes(String des)
    {
        this.des = des;
    }
    public StudentScore getStudentScore()
    {
        return studentScore;
    }
    public void setStudentScore(StudentScore studentScore)
    {
        this.studentScore = studentScore;
    }
    
   
}
