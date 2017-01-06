package com.kaishengit;

import com.kaishengit.pojo.Manager;
import com.kaishengit.utils.SqlSessionFactoryUtil;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by Acer on 2017/1/4.
 *
 */

public class MyBatisTest {
    @Test
    public void readXml(){
        try {
            //1.通过字符输入流读取classpath中的配置文件
            Reader reader = Resources.getResourceAsReader("mybatisconfig.xml");
            //2.构建SqlSessionFactory(接口)对象
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //3.创建SqlSession对象
            SqlSession sqlSession = sqlSessionFactory.openSession();
            //4.执行查询单个对象sql语句
            Manager manager = sqlSession.selectOne("com.kaishengit.mapper.ManagerMapper.findManagerById",1);
            System.out.println(manager);
            //4.释放资源
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void save(){
        //获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        //执行sql语句
        Manager manager  = new Manager();
        manager.setName("lisi");
        manager.setPassword("123123");
        sqlSession.insert("com.kaishengit.mapper.ManagerMapper.save",manager);

        //需要手动提交事务（默认是不自动提交事务）
        sqlSession.commit();
        System.out.println(manager.getId());
        sqlSession.close();
    }
    @Test
    public void del(){
        //获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession(true);//自动提交事务
        //执行sql语句
        sqlSession.delete("com.kaishengit.mapper.ManagerMapper.del",2);
        System.out.println("删除成功");
        //释放资源
        sqlSession.close();
    }
    @Test
    public void update(){
        //获取SqlSession对象
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        //首先查询出来对应的对象
        Manager manager = sqlSession.selectOne("com.kaishengit.mapper.ManagerMapper.findManagerById",1);
        manager.setName("wangwu");
        manager.setPassword("123456");
        //执行sql语句
        sqlSession.update("com.kaishengit.mapper.ManagerMapper.update",manager);
        //对应增删改查需要手动提交事务也可以自动不过需要在获取SqlSession对象时需要设置为true自动提交事务默认手动提交事务
        sqlSession.commit();
        System.out.println("成功修改");
        //释放资源
        sqlSession.close();
    }
    @Test
    public void findAll(){
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        //执行sql语句查询所有返回类型是个集合
        List<Manager> managerList = sqlSession.selectList("com.kaishengit.mapper.ManagerMapper.findAll");
        for (Manager manager : managerList){
            System.out.println(manager);
        }
        //释放资源
        sqlSession.close();
    }
}
