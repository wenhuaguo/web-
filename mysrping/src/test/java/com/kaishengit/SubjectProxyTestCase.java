package com.kaishengit;

import com.kaishengit.proxy.*;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;


import java.lang.reflect.Proxy;


/**
 * Created by Acer on 2017/1/7.
 */
public class SubjectProxyTestCase {
    @Test
    public void sayHello(){
        SubjectProxy subjectProxy = new SubjectProxy();
        subjectProxy.sayHello();
    }

    @Test
    public void invoke(){
        RealTarget realTarget = new RealTarget();
        SubjectInvocationHandler subjectInvocationHandler = new SubjectInvocationHandler(realTarget);
        Subject subject = (Subject) Proxy.newProxyInstance(realTarget.getClass().getClassLoader(),realTarget.getClass().getInterfaces(),subjectInvocationHandler);
        subject.sayHello();
    }

    @Test
    public void cglibProxy(){
        //1.创建enhancer(增强子，强化剂，增加者)对象
        Enhancer enhancer = new Enhancer();
        //目标对象的Class类的对象
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback(new TargetMethodInterceptor());
        Target realTarget = (Target) enhancer.create();//父类指向子类对象
        realTarget.save();
        realTarget.update();
    }
}
