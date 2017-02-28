package com.kaishengit.service.impl;

import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Acer on 2017/1/13.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findAll() {
        //查询所有角色
        List<Role> roleList =  roleMapper.findAll();
        return roleList;
    }

    @Override
    public void add(Integer userId, Integer[] roleIds) {
        addUserRole(userId,roleIds);
    }

    @Override
    public void delByUserId(Integer userId) {
        //删除账户对应的角色
        roleMapper.delRoleByUserId(userId);
    }
    public void addUserRole(Integer userId,Integer[] roleIds){
        if(roleIds != null) {
            for (int i = 0; i < roleIds.length; i++) {
                Role role = roleMapper.findRoleById(roleIds[i]);
                if(role != null) {
                    roleMapper.addUserAndRole(userId, roleIds[i]);
                }
            }
        }
    }
    @Override
    public void editRole(Integer userId, Integer[] roleIds) {
        //删除原有角色
        roleMapper.delRoleByUserId(userId);
        addUserRole(userId,roleIds);
    }
}
