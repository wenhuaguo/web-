package com.kaishengit.mapper;

import com.kaishengit.pojo.User;

/**
 * Created by Acer on 2017/1/10.
 */
public interface UserMapper {
    void save(User user);
    User findById(Integer id);
}
