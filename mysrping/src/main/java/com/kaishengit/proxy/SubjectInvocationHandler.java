package com.kaishengit.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Acer on 2017/1/7.
 * 动态代理模式就是自动的产生代理对象
 * 方式1.jdk方式实现要求目标对象必须有接口，根据接口动态产生实现类，并引用目标对象
 */
public class SubjectInvocationHandler  implements InvocationHandler{
    private Object target;
    public SubjectInvocationHandler(Object target) {
        this.target = target;
    }

    //参数的意义：
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("sayHello".equals(method.getName())) {
            System.out.println("...before....invoke调用，引起");
            Object result = method.invoke(target, args);//代表目标对象方法的执行
            System.out.println("....after.....");
            return result;
        }else {
            return method.invoke(target,args);
        }
    }
}
