package com.kaishengit.service.impl;


import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import com.kaishengit.service.WxService;
import com.kaishengit.utile.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Acer on 2017/1/12.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WxService wxService;

    @Autowired
    private RoleMapper roleMapper;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    //@Value注解可以从Bean管理的配置文件中读取配置文件中的值
    @Value("${password.salt}")
    private String salt;
    @Override
    @Transactional
    public void add(User user, Integer[] roleIds) {
        logger.debug("salt:" + salt);
        String password = DigestUtils.md5Hex(user.getPassword()+salt);
        user.setPassword(password);
        //保存过用户之后
        userMapper.add(user);
        //保存用户和角色关系表应该写在这里
        addUserRole(user, roleIds);
        //保存微信
        com.kaishengit.dto.wx.User user1 = new com.kaishengit.dto.wx.User();
        user1.setUserid(user.getId().toString());
        user1.setDepartment(Arrays.asList(roleIds));
        user1.setMobile(user.getMobile());
        user1.setName(user.getUserName());
        wxService.save(user1);
    }

    private void addUserRole(User user, Integer[] roleIds) {
        if(roleIds != null){
            for (Integer id:roleIds){
                Role role = roleMapper.findRoleById(id);
                if(role != null){
                    roleMapper.addUserAndRole(user.getId(),id);
                }
            }
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userMapper.findAll();
        return userList;
    }

    //删除某一id
    @Override
    public void del(Integer userId) {
        userMapper.del(userId);
    }

    //修改某一对象
    @Override
    public User findById(Integer id) {
        User user = userMapper.findById(id);
        return user;
    }

    //修改对象
    @Override
    @Transactional
    public void update(User user, Integer[] roleIds) {
        if(StringUtils.isNotEmpty(user.getPassword())) {
            user.setPassword(DigestUtils.md5Hex(user.getPassword() + salt));
        }
        userMapper.update(user);
        //删除原因角色
        roleMapper.delRoleByUserId(user.getId());
        //添加新角色
        addUserRole(user,roleIds);
        //修改微信用户
        com.kaishengit.dto.wx.User wxUser = new com.kaishengit.dto.wx.User();
        wxUser.setUserid(user.getId().toString());
        wxUser.setDepartment(Arrays.asList(roleIds));//将数组转换为集合
        wxService.changeUser(wxUser);
    }
    //根据查询条件显示当前页数据
    @Override
    public Page<User> findUserBySearchParam(String queryName, String queryRole, Integer p) {
        int count = userMapper.totals(queryName,queryRole).intValue();
        Page<User> userPage = new Page<>(count,p);
        List<User> userList = userMapper.findUserBySearchParam(queryName,queryRole,userPage.getStart(),userPage.getPageSize());
        userPage.setItems(userList);
        return userPage;
    }

}
