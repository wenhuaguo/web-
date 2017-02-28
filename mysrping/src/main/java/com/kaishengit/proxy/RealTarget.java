package com.kaishengit.proxy;

/**
 * Created by Acer on 2017/1/7.
 * 真实类和代理类一样继承同一个接口
 * 真实类剥离一些代码将这些代码放到代理类中，只专注于方法本身其它事情交给代理类进行管理
 */
public class RealTarget implements Subject {
    public void sayHello() {
        System.out.println("RealSubject sayHello......");
    }

    public void save() {
        System.out.println("realSubject save........");
    }
}
