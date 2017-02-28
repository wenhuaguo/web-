package com.kaishengit.shiro;

import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Acer on 2017/1/20.
 * 基于注解的将类交给Bean容器管理
 * spring内置注解
 * Java内部规范注解
 * @Named
 * JRS-330文件
 * Java
 */
@Named
public class ShiroDbRealm extends AuthorizingRealm {
    //基于注解的IOC注入
    //@AutoWired @Resource @Inject

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    //权限认证法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //返回当前登录的对象
        User user = (User) principalCollection.getPrimaryPrincipal();
        //获取当前对象拥有的角色
        List<Role> roleList = roleMapper.findByUserId(user.getId());
        if (!roleList.isEmpty()) {
            SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
            for (Role role : roleList) {
                simpleAuthenticationInfo.addRole(role.getRoleName());
            }
            return simpleAuthenticationInfo;
        }
        return null;
    }

    //登录认证法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

            //首先进行强制类型转换
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
            String userName = usernamePasswordToken.getUsername();
            User user = userMapper.findByUserName(userName);
            if(user != null){
                     return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
            }
            return null;

    }
}
