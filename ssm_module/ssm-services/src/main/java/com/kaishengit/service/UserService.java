package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.User;

/**
 * Created by Acer on 2017/2/28.
 */
public class UserService {

    public void saveUser(User user){
        UserDao userDao = new UserDao();
        userDao.saveUser(user);
    }
}
