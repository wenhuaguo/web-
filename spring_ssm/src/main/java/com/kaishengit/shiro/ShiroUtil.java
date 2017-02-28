package com.kaishengit.shiro;

import com.kaishengit.pojo.User;
import org.apache.shiro.SecurityUtils;

/**
 * Created by Acer on 2017/1/20.
 */
public class ShiroUtil {
    //获取当前对象
    public static User getCurrentUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    //获取当前登录对象的名字
    public static String getCurrentUserName(){
        return getCurrentUser().getUserName();
    }


    //获取当前用户的id
    public static Integer getCurrentUserId(){
        return getCurrentUser().getId();
    }

    //判断当前登录用户对象是否为市场部员工
    public static boolean isSales(){
        return SecurityUtils.getSubject().hasRole("role_market");
    }
}
