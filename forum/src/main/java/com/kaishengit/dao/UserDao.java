package com.kaishengit.dao;

import com.kaishengit.entity.User;
import com.kaishengit.userVo.UserVo;
import com.kaishengit.util.Dbhelp;
import com.kaishengit.util.Page;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * Created by Acer on 2016/12/15.
 */
public class UserDao {
    /**
     * 去数据库查询账号是否被占用
     * @param username
     * @return
     */
    public User findByName(String username) {
        String sql = "select * from t_user where username = ?";
        return Dbhelp.query(sql,new BeanHandler<>(User.class),username);
    }

    /**
     * 保存数据
     * @param user1
     */
    public void save(User user) {
        String sql = "insert into t_user(username,password,email,phone,state,avatar) values(?,?,?,?,?,?)";
        Dbhelp.update(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getPhone(),user.getState(),user.getAvatar());
    }

    /**
     * 查看邮箱是否已经被注册
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        String sql = "select * from t_user where email = ?";
        return Dbhelp.query(sql,new BeanHandler<>(User.class),email);
    }

    /**
     * 用户进行激活账号
     * @param user
     */
    public void update(User user) {
        System.out.println(user);
        String sql = "update t_user set password=?,email=?,avatar=?,phone=?,state=? where id =  ?";
        Dbhelp.update(sql,user.getPassword(),user.getEmail(),user.getAvatar(),user.getPhone(),user.getState(),user.getId());
    }

    /**
     * 通过id找到对应的用户
     * @param integer
     * @return
     */
    public User findById(Integer id) {
        String sql = "select * from t_user where id=?";
        return Dbhelp.query(sql,new BeanHandler<>(User.class),id);
    }
    //统计总条数(不包括状态不为0的用户)
    public Integer count() {
        String sql = "select count(*) from t_user  where state != 0 order by id";
        return Dbhelp.query(sql,new ScalarHandler<Long>()).intValue();
    }
    //显示页面数据查询所有用户
    public List<User> findAllUser(Page<UserVo> page) {
        String sql = "select * from t_user where state != 0 order by createtime limit ?,?";
        return Dbhelp.query(sql,new BeanListHandler<>(User.class),page.getStart(),page.getPageSize());
    }
    //根据userId查询对应的数据
    public UserVo findUserVo(Integer id) {
        String sql = "select tll.logintime lastLoginTime,tll.ip loginIp,tll.t_user_id userId,tu.username username,tu.createtime,tu.state userState from t_user tu,t_login_log tll where t_user_id =? and t_user_id = tu.id order by logintime desc limit 0,1";
        return Dbhelp.query(sql,new BeanHandler<>(UserVo.class),id);
    }
}
