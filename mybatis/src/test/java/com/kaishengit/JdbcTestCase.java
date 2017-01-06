package com.kaishengit;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Acer on 2017/1/4.
 */
public class JdbcTestCase {
    @Test
    public void test() throws Exception{
        //1.加载数据库驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///db_1","root","root");
        //3.执行sql语句
        PreparedStatement preparedStatement = null;
        //slq默认是自动提交事务现在变成手动作业
        connection.setAutoCommit(false);
        try {
            String sql = "insert into manager(name,password) values(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"a1");
            preparedStatement.setString(2,"123123");
            preparedStatement.executeUpdate();

            String sql1 = "insert into manager(name,password) values('a2','123123')";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();//如果不成功事务回滚,也就是一方不成功就全部退回不改变数据库
        }finally {
            preparedStatement.close();
            connection.close();
        }

    }
}
