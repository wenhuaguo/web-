package com.kaishengit.dao;

import com.kaishengit.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;//静态导入

/**
 * Created by Acer on 2016/12/13.
 */
public class UserDaoTest {
    @Test
    public void findAll() throws Exception {
        List<User> userList = userDao.findAll();
        assertEquals(10,userList.size());
    }

    private UserDao userDao;
    @Before//@before注解在所有测试和方法之前执行
    public void before(){
        userDao =  new UserDao();
    }
    @Test
    public void findByid() throws Exception {
        User user = userDao.findByid(7);
        assertNotNull(user);//笃定怎样怎样断言类Assert
    }
    @Test
    public void testDdd() throws Exception {
        User user = new User();
        user.setName("jack");
        user.setAge(10);
        user.setAddress("河南焦作");
        user.setPassword("123123");
        userDao.add(user);
    }
    @Test
    public void TestDel(){
        userDao.del(9);

    }


}