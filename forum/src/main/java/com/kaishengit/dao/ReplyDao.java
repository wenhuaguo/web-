package com.kaishengit.dao;

import com.kaishengit.entity.Reply;
import com.kaishengit.entity.User;
import com.kaishengit.util.Dbhelp;
import com.kaishengit.util.readconfig.ReadConfig;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Acer on 2016/12/21.
 */
public class ReplyDao {

    /**
     * 保存用户恢复的数据
     * @param reply
     */
    public void saveReply(Reply reply) {
        String sql = "insert into t_reply(content,userid,postid) values(?,?,?)";
        Dbhelp.update(sql,reply.getContent(),reply.getUserid(),reply.getPostid());
    }

    /**
     * 根据postId查询对应的回复
     * @param integer
     * @return
     */
    public List<Reply> findByPostId(Integer postId) {
        String sql = "SELECT user.id,user.username,user.avatar,reply.* FROM t_user USER,t_reply reply WHERE reply.userid=user.id AND postid=? ORDER BY replytime ASC";
        //使用匿名局部内部类
        return Dbhelp.query(sql,new AbstractListHandler<Reply>(){
            @Override
            protected Reply handleRow(ResultSet resultSet) throws SQLException {
               Reply reply = new BasicRowProcessor().toBean(resultSet,Reply.class);
                User user = new User();
                user.setAvatar(ReadConfig.get("qiniu.internate")+ resultSet.getString("avatar"));
                user.setUsername(resultSet.getString("username"));
                user.setId(resultSet.getInt("id"));
                reply.setUser(user);
                return reply;
            }
        },postId);
    }
    //删除该贴对应的回复
    public void deleteReplyByPostId(Integer postId) {
        String sql = "delete  from t_reply where postid = ?";
        Dbhelp.update(sql,postId);
    }
}
