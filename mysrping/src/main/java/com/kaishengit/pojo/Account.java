package com.kaishengit.pojo;

/**
 * Created by Acer on 2017/1/10.
 */
public class Account {
    private String userName;
    private Integer age;

    public Account() {
    }

    public Account(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
