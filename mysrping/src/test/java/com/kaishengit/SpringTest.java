package com.kaishengit;


import com.kaishengit.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Acer on 2017/1/9.
 * 基于注解的spring单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class SpringTest {
    @Test
    public void context(){
        //基于注解的创建spring容器
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
       //参数类型为交给Bean管理的类
        UserDao userDao = (UserDao) applicationContext.getBean("userDaoImpl");
//        userDao.update();
    }
}
