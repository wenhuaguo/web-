package com.kaishengit.pojo;


import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Acer on 2017/1/12.
 */

public class User implements Serializable {
    private Integer id;
    private String userName;
    private String password;
    private List<Role> roleList;
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //创建一个get方法客户端获取
    public String getRoleNames(){
        List<String> viewNames = Lists.newArrayList(Collections2.transform(getRoleList(), new Function<Role,String>() {
            @Override
            public String apply(Role role) {
                System.out.println(role.getViewName());
                return role.getViewName();
            }
        }));
        StringBuilder stringBuilder = new StringBuilder();
        for (String viewName : viewNames){
            stringBuilder.append(viewName).append(" ");
        }
        return stringBuilder.toString();
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
