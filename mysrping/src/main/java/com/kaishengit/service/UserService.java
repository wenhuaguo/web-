package com.kaishengit.service;

import com.kaishengit.pojo.User;

/**
 * Created by Acer on 2017/1/7.
 */
public interface UserService {
    void save(User user) throws Exception;
    User findById(Integer id);
}
