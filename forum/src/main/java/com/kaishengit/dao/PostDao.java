package com.kaishengit.dao;

import com.kaishengit.entity.Post;
import com.kaishengit.entity.PostReplyCount;
import com.kaishengit.entity.User;
import com.kaishengit.util.Dbhelp;
import com.kaishengit.util.Page;
import com.kaishengit.util.readconfig.ReadConfig;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Acer on 2016/12/20.
 */
public class PostDao {
    /**
     * 对用户发的新帖进行保存
     * @param newPost
     */
    public Integer save(Post newPost) {
        String sql = "insert into t_newpost(title,content,userid,nodeid,lastreplytime) values(?,?,?,?,?)";
        return Dbhelp.insert(sql,newPost.getTitle(),newPost.getContent(),newPost.getUserid(),newPost.getNodeid(),newPost.getLastreplytime());
    }

    /**
     * 通过postid查找用户发的新帖作用是在用户界面显示用户发的帖的详细内容
     * @param postid
     */
    public Post findById(Integer postId) {
        String sql = "select * from t_newpost where id=?";
        return Dbhelp.query(sql,new BeanHandler<>(Post.class),postId);
    }

    /**
     * 对数据库信息进行更新
     * @param post
     */
    public void update(Post post) {
        String sql = "update t_newpost set title=?,content=?,clicknum=?,savenum=?,thanknum=?,replynum=?,lastreplytime=? where id=?";
        Dbhelp.update(sql,post.getTitle(),post.getContent(),post.getClicknum(),post.getSavenum(),post.getThanknum(),post.getReplynum(),post.getLastreplytime(),post.getId());
    }

    /**
     * 查询所有帖子的数量
     */
    public int findAllPostNum() {
        String sql = "select count(*) from t_newpost";
        return Dbhelp.query(sql,new ScalarHandler<Long>()).intValue();
    }

    /**
     * 当前页显示的数据
     * @param start
     * @param pageSize
     * @return
     */


    /**
     * 显示某一节点下面的数据这是通过post的聚合函数进行查询,而不是通过node表节点下面的nodenum应为目前不准确
     * @param nodeid
     * @return
     */
    public int findNodeIdNum(Integer nodeid) {
        String sql = "select count(*) from t_newpost where nodeid = ?";
       //将long类型的数据转换为int类型 long.intValue()
        return Dbhelp.query(sql,new ScalarHandler<Long>(),nodeid).intValue();
    }
    //查询当前页面显示的数据内容
    public List<Post> findPostList(HashMap<String,Object> map) {
        String sql = "select tu.username,tu.avatar,tp.* from t_user tu,t_newpost tp where tu.id=tp.userid ";
        //通过三元表达式判断nodeid 是否为空
        String nodeid = map.get("nodeid") == null ? null:String.valueOf(map.get("nodeid")) ;
        //拼接字符串where后面
        String where = "";
        List<Object> list = new ArrayList<>();
        if(StringUtils.isNotEmpty(nodeid)){
            where += "and nodeid=? ";
            list.add(nodeid);
        }
        //拼接字符串
        where += "order by tp.lastreplytime desc limit ?,?";
        list.add(map.get("star"));
        list.add(map.get("pageSize"));
        sql += where;
        return Dbhelp.query(sql,new AbstractListHandler<Post>() {
                    @Override
                    protected Post handleRow(ResultSet resultSet) throws SQLException {
                        Post post = new BasicRowProcessor().toBean(resultSet,Post.class);
                        User user = new User();
                        user.setAvatar(ReadConfig.get("qiniu.internate") + resultSet.getString("avatar"));
                        user.setUsername(resultSet.getString("username"));
                        user.setId(resultSet.getInt("userid"));
                        post.setUser(user);
                        return post;
            }
        },list.toArray());
    }
    //根据对应的postId删除帖子
    public void deletePost(Post post) {
        String sql = "delete from t_newpost where id = ?";
        Dbhelp.update(sql,post.getId());
    }
    //通过日期统计有多少天
    public int countByDate() {
        String sql = "select count(*) from (select count(*) from t_newpost group by DATE_FORMAT(createtime,'%y-%y-%m')) as postCount";
        return Dbhelp.query(sql,new ScalarHandler<Long>()).intValue();
    }
    //页面显示数据
    public List<PostReplyCount> findAllPostReply(Page<PostReplyCount> postReplyCountPage) {
        String sql =  "SELECT COUNT(*) newPostCount,DATE_FORMAT(createtime,'%y-%m-%d') 'date',\n" +
                "(SELECT COUNT(*) FROM t_reply WHERE DATE_FORMAT(createtime,'%y-%m-%d') \n" +
                "= DATE_FORMAT(t_newpost.createtime,'%y-%m-%d')) 'newReplyCount'\n" +
                "FROM t_newpost GROUP BY (DATE_FORMAT(createtime,'%y-%m-%d')) \n" +
                "ORDER BY (DATE_FORMAT(createtime,'%y-%m-%d')) DESC limit ?,?;";
        return Dbhelp.query(sql,new BeanListHandler<PostReplyCount>(PostReplyCount.class),postReplyCountPage.getStart(),postReplyCountPage.getPageSize());
    }
}
