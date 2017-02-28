package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Acer on 2017/1/10.
 * 基于spring的单元测试
 * 读取配置文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class UserMapperTestCase {
    @Autowired
    private UserService userService;

    @Test
    public void save() throws Exception{
        User user = new User("王五","123123");
        userService.save(user);
        System.out.println(user.getId());
    }
    @Test
    public void findById(){
        User user = userService.findById(1);
        System.out.println(user);
    }
}
