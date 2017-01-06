package com.kaishengit.service;

import com.kaishengit.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Acer on 2016/12/14.
 */
public class UserServiceWithGuavaTest {
    @Test
    public void findById1() throws Exception {
        User user = userService.findById(7);
        user = userService.findById(7);
        System.out.println(user);
    }

    private UserServiceWithGuava userService = new UserServiceWithGuava();
    @Test
    public void findById() throws Exception {
            User user = userService.findById(7);
            user = userService.findById(7);
        System.out.println(user);
    }

}