package com.kaishengit.dao;

import com.kaishengit.pojo.User;

/**
 * Created by Acer on 2017/2/28.
 * 用于更改数据通过slq语句
 */
public class UserDao {
    public void saveUser(User user) {
        System.out.println(user.getName() + "保存成功");
    }
}
