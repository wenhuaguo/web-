package com.kaishengit.proxy;

/**
 * Created by Acer on 2017/1/7.
 * 代理类和实际类继承同一个接口
 * 代理类可以是对真实类的增强写一些自己的代码
 *
 */
public class SubjectProxy implements Subject{
    //创建实际对象的实例
    private RealTarget realTarget = new RealTarget();
    public void sayHello() {
        //在实现接口的方法中增加一些代码
        System.out.println("开启事务");
        try {

            realTarget.sayHello();
            System.out.println("事务提交");
        }catch (Exception ex){
            System.out.println("回滚事务");
        }finally {
            System.out.println("释放资源");
        }
    }

    public void save() {

    }
}
