package com.kaishengit.dao;

import com.kaishengit.pojo.User;

import java.util.List;

/**
 * Created by Acer on 2017/1/7.
 */
public interface UserDao {
    void save(User user);
    void del(Integer id);
    void update(User user);
    User findById(Integer id);
    List<User> findAll();
    User findByName(String userName);
    long count();
}
