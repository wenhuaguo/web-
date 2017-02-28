package com.kaishengit.proxy;

/**
 * Created by Acer on 2017/1/9.
 * CGLib动态代理模式要求目标对象没有接口
 */
public class Target {
    public void save(){
        System.out.println("target save 。。。。。");
    }
    public void update(){
        System.out.println("target update .........");
    }
}
