package com.kaishengit.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Acer on 2017/1/4.
 */
public class SqlSessionFactoryUtil {
    //单例模式只能new出来一个对象
    //获得SqlSessionFactory对象
    private static SqlSessionFactory sqlSessionFactory = buildSqlSessionFactory();
    //创建SqlSeesionFactory对象
    private static SqlSessionFactory buildSqlSessionFactory(){
        try {
            //通过字符输入流读取配置文件中的内容
            Reader reader = Resources.getResourceAsReader("mybatisconfig.xml");
            //创建SqlSessionFactory对象
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("读取配置文件异常",e);
        }
    }
    //获得SqlSessionFactory对象通过方法
    public SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
    //创建SqlSession对象
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
    public static SqlSession getSqlSession(Boolean isAutoCommit){
        return sqlSessionFactory.openSession(isAutoCommit);//获得一个自动提交SqlSession对象
    }
}
