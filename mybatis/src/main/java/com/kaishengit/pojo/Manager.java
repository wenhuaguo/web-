package com.kaishengit.pojo;

import java.io.Serializable;

/**
 * Created by Acer on 2017/1/4.
 */
public class Manager implements Serializable{
    private Integer id;
    private String name;
    private String password;

    public Manager() {
    }

    public Manager(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
