package com.kaishengit.service;

import com.kaishengit.pojo.User;
import com.kaishengit.utile.Page;

import java.util.List;

/**
 * Created by Acer on 2017/1/12.
 */
public interface UserService {

    void add(User user, Integer[] roleIds);
    List<User> findAll();

    void del(Integer userId);

    User findById(Integer id);

    void update(User user, Integer[] roleIds);

    Page<User> findUserBySearchParam(String queryName, String queryRole, Integer p);
}
