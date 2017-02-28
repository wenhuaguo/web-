package com.kaishengit.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Acer on 2017/1/9.
 * 动态代理模式的CGLib实现（不要求目标对象有接口,动态产生目标对象的子类，调用目标对象的方法）
 * 实现MethodInterceptor接口
 */
public class TargetMethodInterceptor implements MethodInterceptor{
    public Object intercept(Object target, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("....before....");
        //两个参数一个是目标对象,一个是参数数组
        Object result = methodProxy.invokeSuper(target,objects);//目标对象方法的执行
        System.out.println("....after....");
        return result;
    }
}
