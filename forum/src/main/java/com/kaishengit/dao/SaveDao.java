package com.kaishengit.dao;

import com.kaishengit.entity.Save;
import com.kaishengit.util.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by Acer on 2016/12/23.
 */
public class SaveDao {
    /**
     * 保存加入收藏功能
     * @param save
     */
    public void saveFav(Save save) {
        String sql = "insert into t_save(userid,postid) values(?,?)";
        Dbhelp.update(sql,save.getUserid(),save.getPostid());

    }

    /**
     * 根据userId和postId查询是否存在并进行取消收藏
     * @param userId
     * @param integer
     * @return
     */
    public Save findByPostIdAndUserId(Integer userId, Integer postId) {
        String sql = "select * from t_save where userid=? and postid=?";
        return Dbhelp.query(sql,new BeanHandler<>(Save.class),userId,postId);
    }

    /**
     * 删除对应的数据记录
     */
    public void deleteFav(Save save) {
        String sql = "delete from t_save where userid=? and postid = ?";
        Dbhelp.update(sql,save.getUserid(),save.getPostid());
    }
}
