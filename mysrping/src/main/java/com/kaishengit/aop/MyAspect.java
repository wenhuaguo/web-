package com.kaishengit.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import org.springframework.stereotype.Component;

/**
 * Created by Acer on 2017/1/9.
 * 创建一个通知类交给Bean管理单例模式基于注解的Bean管理
 * 基于注解的aop(面向切面编程)代码的分离本质上是在一起的
 */
@Component
@Aspect
public class MyAspect {
    //创建一个方法没有方法体作为切入表达式用第一个..表示service包及其子包切入点表达式
    @Pointcut("execution(* com.kaishengit.service..*.*(..))")
    public void pointcut(){}

    //引用创建的方法（注解表达式）本质上是value值一个参数可以省略value值两个不可以省略
    //也可以写切入点表达式
//    @Before("pointcut()")
//    public void beforeAdvice(){
//        System.out.println("前置通知");
//    }
//    @AfterReturning(value = "pointcut()",returning = "result")
//    public void afterAdvice(Object result){
//        System.out.println("后置通知" + result);
//    }
//    @AfterThrowing(value = "pointcut()",throwing = "ex")
//    public void exceptionAdvice(Exception ex){
//        System.out.println("异常通知" + ex.getMessage());
//    }
//    @After("pointcut()")
//    public void finallyAdvice(){
//        System.out.println("最终通知");
//    }

    //环绕通知就是前面几个通知的综合
    @Around("pointcut()")
    public void aroundAdvice(ProceedingJoinPoint joinPoint){
        try{
            System.out.println("。。前置通知。。");
            Object object = joinPoint.proceed();//目标对象方法的执行
            System.out.println("。。后置通知。。。。" + object);
        }catch (Throwable throwable){
            System.out.println("。。异常通知。。" +throwable);
        }finally {
            System.out.println("。。最终通知。。");
        }
    }
}
