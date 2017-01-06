package com.kaishengit.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Acer on 2016/12/14.
 */
public class UserServiceWithGuava {
    private UserDao userDao = new UserDao();
    //缓存要是唯一的
    private static Cache<String,User> cache = CacheBuilder
            .newBuilder()
            .maximumSize(100)
            .expireAfterAccess(10,TimeUnit.MINUTES)
            .build();
            public User findById(Integer id){
                User user = null;
                try {
                    //get(key,Callable<v>)返回缓存中key相对应的值，
                    user = cache.get("user:" + id, new Callable<User>() {
                        @Override
                        public User call(){
                            return userDao.findByid(id);
                        }
                    });
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return user;
            }
//    //写成静态的一份一直存在在内存中，进行链式调用
//    private static LoadingCache<String,User> cache = CacheBuilder
//            .newBuilder().maximumSize(100)
//            .expireAfterAccess(50, TimeUnit.SECONDS)
//            .build(
//              //匿名局部内部类
//              new CacheLoader<String, User>() {
//                @Override
//                public User load(String key)  {
                    //load方法会抛异常
//                    UserDao userDao = new UserDao();
//                    return userDao.findByid(Integer.valueOf(key));//Integer.valueOf(参数可为int或String)意思是将int类型或者String类型转换为Integer类型
//
//        }
//    });
//    public User findById(Integer id){
    //第一次取值内存中没有，会调用 new CacheLoader<String, User>方法进行放入内存中，然后再冲内存中调用
        //如果不想try{}catch{}就将load方法抛的异常删掉，并将cache.get(id.toString());方法转换为getUnchecked(id.toString());方法不能混用
//        User user = cache.getUnchecked(id.toString());//id.toString()将Integer类型转换为字符串类型
//        return user;
//    }
}
