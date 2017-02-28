package com.kaishengit;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by Acer on 2017/1/9.
 * 替代applicationContext.xml配置文件
 * 基于注解的配置文件
 * 一般不用基于注解的配置太激进可以在配置类中进行一些配置
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class Application {
}
