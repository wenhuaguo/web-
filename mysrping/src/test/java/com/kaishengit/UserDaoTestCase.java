package com.kaishengit;

import com.kaishengit.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Acer on 2017/1/7.
 */
public class UserDaoTestCase {
    @Test
    public void save(){
        //从spring容器中获取Bean
        //1.获取spring容器
        //2.从spring容器中获取Bean可以通过实体类（UserDaoImpl接）也可以利用多态通过实现类的接口接,通过实体类的映射不用进行强转
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
//        UserDao userDao = (UserDao) applicationContext.getBean("userDaoImpl");
//        userDao.update();
//        userDao.save();
//        userDao.del();
        //怎样判断两个对象是否是同一个对象看创建出来的对象是否相等
//        UserDao userDao = applicationContext.getBean("userDaoImpl",UserDaoImpl.class);
//        UserDao userDao1 = applicationContext.getBean("userDaoImpl",UserDaoImpl.class);
        //两个用==比较的是内存地址，用equals比较的是内容
//        System.out.println(userDao == userDao1);
        //userDao.save();
    }
}
