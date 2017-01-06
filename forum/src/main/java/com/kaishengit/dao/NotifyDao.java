package com.kaishengit.dao;

import com.kaishengit.entity.Notify;
import com.kaishengit.entity.User;
import com.kaishengit.util.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * Created by Acer on 2016/12/26.
 */
public class NotifyDao {
    //查询发帖userId对应的所有回复的消息（回复的回复暂不考虑在内）
    public List<Notify> findAllNotify(User user) {
        String sql = "select * from t_notify where userid = ? ORDER BY readtime, createtime";
        return Dbhelp.query(sql,new BeanListHandler<>(Notify.class),user.getId());
    }
    //保存用户的回复然后给发帖者发送消息
    public void saveNotify(Notify notify) {
        String sql = "insert into t_notify(content,userid,state) values(?,?,?)";
        Dbhelp.update(sql,notify.getContent(),notify.getUserid(),notify.getState());
    }
    //通过id查找对应的通知消息
    public Notify findNotifyById(String s) {
        String sql = "select * from t_notify where id = ?";
        return Dbhelp.query(sql,new BeanHandler<>(Notify.class),Integer.valueOf(s));
    }
    //更新notify的内容
    public void updateNotify(Notify notify) {
        String sql = "update t_notify set state=?,readtime=? where id=?";
        Dbhelp.update(sql,notify.getState(),notify.getReadtime(),notify.getId());
    }
}
