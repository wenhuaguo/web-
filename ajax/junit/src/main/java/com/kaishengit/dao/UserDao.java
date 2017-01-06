package com.kaishengit.dao;

import com.kaishengit.entity.User;
import com.kaishengit.util.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by Acer on 2016/12/12.
 */
public class UserDao {
     public User findByid(Integer id){
         String sql = "select * from user where id = ?";
         return Dbhelp.query(sql,new BeanHandler<>(User.class),id);
     }
     public void add(User user){
         String sql = "insert into user(name,age,address,password) values(?,?,?,?)";
         Dbhelp.update(sql,user.getName(),user.getAge(),user.getAddress(),user.getPassword());
     }
     public List<User> findAll(){
         String sql = "select * from user";
         return Dbhelp.query(sql,new BeanListHandler<>(User.class));
     }
     public void del(Integer id){
         String sql = "delete from user where id = ?";
         Dbhelp.update(sql,id);
     }
}
