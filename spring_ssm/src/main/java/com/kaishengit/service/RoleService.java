package com.kaishengit.service;

import com.kaishengit.pojo.Role;

import java.util.List;

/**
 * Created by Acer on 2017/1/13.
 */
public interface RoleService {
    List<Role> findAll();

    void add(Integer userId, Integer[] roleIds);

    void delByUserId(Integer userId);

    void editRole(Integer id, Integer[] roleIds);
}
