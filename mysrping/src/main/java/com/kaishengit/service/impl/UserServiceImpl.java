package com.kaishengit.service.impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Acer on 2017/1/9.
 * 基于注解的Bean管理service层一般用@Service
 */
@Service
public class UserServiceImpl implements UserService {
    //自动注入
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional
    public void save(User user) throws Exception {
        userMapper.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
