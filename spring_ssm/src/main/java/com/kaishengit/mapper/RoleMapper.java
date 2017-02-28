package com.kaishengit.mapper;

import com.kaishengit.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Acer on 2017/1/13.
 */
public interface RoleMapper {
    List<Role> findAll();


    void addUserAndRole(@Param("userId") Integer userId,@Param("roleId") Integer integer);

    void delRoleByUserId(Integer userId);

    Role findRoleById(Integer roleId);

    List<Role> findByUserId(Integer userId);
}
