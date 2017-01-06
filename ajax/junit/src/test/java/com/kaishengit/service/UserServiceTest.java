package com.kaishengit.service;

import com.kaishengit.entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Acer on 2016/12/13.
 */
public class UserServiceTest {
    private UserService userService = new UserService();
    @Test
    public void findByid() throws Exception {
        User user = userService.findByid(7);
        user = userService.findByid(7);
        System.out.println(user);
    }

    @Test
    public void findAll() throws Exception {
        List<User> userList = userService.findAll();
        userList = userService.findAll();
        System.out.println(userList);
    }

    @Test
    public void add() throws Exception {
        List<User> userList = userService.findAll();
        int beforSize = userList.size();
        System.out.println("before:" + beforSize);

        User user = new User();
        user.setName("张三");
        user.setAddress("天津");
        user.setAge(10);
        user.setPassword("123123");
        userService.add(user);
        List<User> userList1 = userService.findAll();
        int size = userList1.size();
        System.out.println("size:" + size);
    }

    @Test
    public void del() throws Exception {
        List<User> userList = userService.findAll();
        int beforeSize = userList.size();
        System.out.println("before:" + beforeSize);
        userService.del(18);
        List<User> userList1 = userService.findAll();
        System.out.println("after:"+userList1.size());
    }

}