package com.kaishengit.dao;

import com.kaishengit.entity.Thank;
import com.kaishengit.util.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by Acer on 2016/12/27.
 */
public class ThankDao {
    //根据userid和postid查询是否当前登录用户已经感谢该帖子
    public Thank findByUserIdAndPostId(Integer userid, Integer postid) {
        String sql = "select * from t_thank where userid=? and postid = ?";
        return Dbhelp.query(sql,new BeanHandler<>(Thank.class),userid,postid);
    }
    //对感谢进行保存
    public void save(Integer userid, String postid) {
        String sql = "insert into t_thank(userid,postid) values(?,?)";
        Dbhelp.update(sql,userid,Integer.valueOf(postid));
    }
    //删除感谢
    public void delete(Integer userid, String postid) {
        String sql = "delete from t_thank where userid=? and postid=?";
        Dbhelp.update(sql,userid,Integer.valueOf(postid));
    }

}
