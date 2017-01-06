package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.dao.*;
import com.kaishengit.entity.*;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Page;
import com.kaishengit.util.readconfig.ReadConfig;
import org.apache.commons.lang3.StringUtils;




import java.sql.Timestamp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Acer on 2016/12/20.
 */
public class PostService {
    private PostDao postdao = new PostDao();
    private NodeDao nodedao = new NodeDao();
    private UserDao userdao = new UserDao();
    private ReplyDao replydao = new ReplyDao();
    private SaveDao savedao = new SaveDao();
    private NotifyDao notifyDao = new NotifyDao();
    private ThankDao thankDao = new ThankDao();
    /**
     * 对用户发布的新帖进行保存到数据库
     * @param node
     * @param user
     * @param title
     * @param content
     */
    public Post saveUserNewPost(Integer node, Integer userid, String title, String content) {
        Post newPost = new Post();
        newPost.setContent(content);
        newPost.setNodeid(node);
        newPost.setTitle(title);
        newPost.setUserid(userid);
        //暂时设置最后回复时间为当前时间
        //获取1970年至现在的毫秒数
        System.out.println("毫秒数："+ System.currentTimeMillis());
        newPost.setLastreplytime(new Timestamp(System.currentTimeMillis()));
        //根据保存过程中返回来的id将id对应的帖的详细内容查询出来
        Integer postId = postdao.save(newPost);
        System.out.println(postId);
        //?????是否应该去数据库再查询一遍
        newPost.setId(postId);
        //每当用户发帖都会有所有分类节点那么我们就可以更新node节点中某一节点的发帖数量
        Node nodeCount = nodedao.findByNodeId(node);
        if(nodeCount != null){
            nodeCount.setNodecount(nodeCount.getNodecount() +1);
            nodedao.update(nodeCount);
        }else {
            throw new ServiceException("该节点不存在");
        }
        //返回用户发布的新帖的详细内容供显示
        return newPost;
    }

    /**
     * 读取数据库中的节点
     */
    public List<Node> readNode() {
        return nodedao.readNode();
    }

    /**
     * 根据postid
     * @param
     */
    public Post findPostById(String postId) {
        if(StringUtils.isNumeric(postId)){
            Post post = postdao.findById(Integer.valueOf(postId));
            if(post != null){
                User user  = userdao.findById(post.getUserid());
                Node node = nodedao.findByNodeId(post.getNodeid());
                //将七牛连接设置为可更改的
                user.setAvatar(ReadConfig.get("qiniu.internate") + user.getAvatar());
                post.setUser(user);
                post.setNode(node);
                return post;
            }else {
                throw new ServiceException("该贴不存在或已被删除");
            }
        }else {
            throw new ServiceException("参数错误");
        }
    }

    /**
     * 保存用户的回复内容
     * @param postId
     * @param content
     * @param userId
     */
    public void saveReply(String postId, String content, Integer userId) {
        if(StringUtils.isNumeric(postId)) {
            Post post = postdao.findById(Integer.valueOf(postId));
            if(post != null) {
                Reply reply = new Reply();
                reply.setUserid(userId);
                reply.setContent(content);
                reply.setPostid(Integer.valueOf(postId));
                replydao.saveReply(reply);
                //新增消息通知自己给自己的回复不用进行通知
                if(!userId.equals(post.getUserid())) {
                    Notify notify = new Notify();
                    //内容里面是个字符串进行拼接了里面的连接会转到有详解的页面
                    notify.setContent("你的主题<a href=\"/showPost?postId="+post.getId()+"\">["+post.getTitle()+"]</a>有了新的回复，请查看.");
                    notify.setUserid(post.getUserid());
                    notify.setState(Notify.UNREAD_STATE);
                    notifyDao.saveNotify(notify);
                }
                post.setLastreplytime(new Timestamp(System.currentTimeMillis()));
                System.out.println("beforePostreplynum:" + post.getReplynum());
                post.setReplynum(post.getReplynum() + 1);
                System.out.println("afterReplynum:" + post.getReplynum());
                postdao.update(post);
            }else {
                throw new ServiceException("该贴不存在或已被删除不能回复");
            }
        }else {
            throw new ServiceException("参数错误");
        }
    }

    /**
     * 统计点击次数
     * @param postId
     */
    public void clickCount(String postId) {
        if(StringUtils.isNumeric(postId)){
            Post post = postdao.findById(Integer.valueOf(postId));
            if(post != null){
                post.setClicknum(post.getClicknum()+1);
                postdao.update(post);
            }else {
                throw new ServiceException("该贴不存在或已被删除");
            }
        }else {
            throw new ServiceException("参数错误");
        }
    }


    /**
     * 显示某一帖的所有回复连表查询
     * @param postId
     * @return
     */
    public List<Reply> findAllReply(String postId) {
        if(StringUtils.isNumeric(postId)){
            List<Reply> replyList = replydao.findByPostId(Integer.valueOf(postId));
            if(replyList != null){
                return replyList;
            }else {
                throw new ServiceException("该贴不存在或已被删除");
            }
        }else {
            throw new ServiceException("参数错误");
        }
    }

    /**
     * 对加入收藏和取消收藏按钮点击进行处理
     * @param type
     * @param postId
     * @param userId
     */
    public Post savePost(String postId , Integer userId) {
        Post post = postdao.findById(Integer.valueOf(postId));
        if(post != null) {
            Save save = new Save();
            save.setPostid(post.getId());
            save.setUserid(userId);
            savedao.saveFav(save);
            //保存成功post的savenum+1;
            post.setSavenum(post.getSavenum() + 1);
            postdao.update(post);

        }
        return post;

    }

    /**
     * 取消收藏
     * @param postId
     * @param userId
     */
    public Post deleteFave(String postId, Integer userId) {
        Integer postid = Integer.valueOf(postId);
        Post post= null;
        Save saveFav = savedao.findByPostIdAndUserId(userId, postid);
        if (saveFav != null) {
            savedao.deleteFav(saveFav);
            post = postdao.findById(postid);
            post.setSavenum(post.getSavenum() - 1);
            postdao.update(post);

        }
        return post;
    }

    /**
     * 判断登录用户是否收藏该帖子
     * @param postId
     * @param id
     */
    public Save findSaveByUserIdAndPostId(String postId, Integer userId) {
        if(StringUtils.isNotEmpty(postId) && StringUtils.isNumeric(postId)){
            Post post = postdao.findById(Integer.valueOf(postId));
                if(post != null){
                    Save save = savedao.findByPostIdAndUserId(userId,Integer.valueOf(postId));
                        return save;
                }else {
                    throw new ServiceException("该贴不存在");
                }

        }else {
            throw new ServiceException("参数错误");
        }
    }

    /**
     * 编辑帖子
     * @param currTimeStamp
     * @param postId
     * @param user
     * @param s
     * @param node
     */
    public Post editPost(String postId, User user,String nodeid, String content,String title) {
        Integer postid = Integer.valueOf(postId);
        if(StringUtils.isNotEmpty(postId) && StringUtils.isNumeric(postId)){
            Post post = postdao.findById(postid);
            if(post != null){
                //如果帖子存在并且返回值为true说明允许修改，如果为false不允许修改
                if(post.isEdit()){
                    post.setTitle(title);
                    post.setNodeid(Integer.valueOf(nodeid));
                    post.setContent(content);
                    postdao.update(post);
                    //更新nodeId中nodeNum中的数量
                    Node node = nodedao.findByNodeId(Integer.valueOf(nodeid));
                    node.setNodecount(node.getNodecount() + 1);
                    nodedao.update(node);
                    return post;
                }else {
                    throw new ServiceException("更改时间已过不允许修改");
                }
            }else {
                throw new ServiceException("参数错误");
            }
        }else {
            throw new ServiceException("参数错误");
        }
    }

    /**
     * 编辑的时候根据nodeId减对应的nodeNum
     * @param nodeid
     */
    public void deleteNode(Integer nodeid) {
        Node node = nodedao.findByNodeId(nodeid);
        node.setNodecount(node.getNodecount() -1);
        nodedao.update(node);
    }

    /**
     * 根据当前页面查询帖子
     * @param pageNo
     * @param nodeid
     */
    public Page<Post> findPostByPageNo(int pageNo, String nodeid) {
        System.out.println("pageNo:"+pageNo);
        //创建一个map集合
        HashMap<String,Object> map = Maps.newHashMap();
        //声明一个变量用于记录不同节点的贴量
        //查询所有（全部或者某一节点）帖子的数量
        int count = 0;
        if(StringUtils.isEmpty(nodeid)){
            count = postdao.findAllPostNum();
            System.out.println("allCount:" + count);
        }else {
            count = postdao.findNodeIdNum(Integer.valueOf(nodeid));
            System.out.println("nodeIdCount:" + count);
        }
        Page<Post> page = new Page<>(count,pageNo);
        System.out.println("star:" + page.getStart()  + "pagesize:" + page.getPageSize());
        map.put("nodeid",nodeid);
        //将搜索开始的起始行传进去
        map.put("star",page.getStart());
        //将pagesize每页显示的数量传进去
        map.put("pageSize",page.getPageSize());
        //查询当前页面显示的数据传经去一个map集合
        List<Post> postList = postdao.findPostList(map);
        System.out.println("postList:" +postList);
        page.setItems(postList);
        return page;
    }
    //查看当前用是否感谢该主题
    public Thank findThankByUserIdAndPostId(Integer userid, String postid) {
        if(StringUtils.isNotEmpty(postid) && StringUtils.isNumeric(postid)){
            Post post = postdao.findById(Integer.valueOf(postid));
            if(post != null){
                Thank thank = thankDao.findByUserIdAndPostId(userid,Integer.valueOf(postid));
                return thank;
            }else {
                throw new ServiceException("该贴不存在或已被删除");
            }
        }else {
            throw new ServiceException("参数错误");
        }
    }
    //取消感谢和感谢
    public Post thank(String type, String postid, Integer userid) {

        if(StringUtils.isNumeric(postid)){
            Post post = postdao.findById(Integer.valueOf(postid));
            Thank thank = new Thank();
            if(post != null){
                if("thank".equals(type)){
                    thankDao.save(userid,postid);
                    post.setThanknum(post.getThanknum() + 1);
                    postdao.update(post);

                }else if("remove".equals(type)){
                    thankDao.delete(userid,postid);
                    post.setThanknum(post.getThanknum() -1);
                    postdao.update(post);
                }
                return post;
            }else {
                throw new ServiceException("该贴不存在或已被删除");
            }
        }else {
            throw new ServiceException("参数错误");
        }

    }
    //根据PostId删除对应主题
    public void deletePost(String postId) {
        if(StringUtils.isNotEmpty(postId) && StringUtils.isNumeric(postId)){
            Post post = postdao.findById(Integer.valueOf(postId));
            if(post != null){

                //减少该节点对应的主题数量
                Node node = nodedao.findByNodeId(post.getNodeid());
                node.setNodecount(node.getNodecount() - 1);
                nodedao.update(node);
                //根据postId删除该贴对应的回复
                replydao.deleteReplyByPostId(post.getId());
                postdao.deletePost(post);
            }else {
                throw new ServiceException("该贴不存在或已被删除");
            }
        }else {
            throw new ServiceException("参数异常");
        }
    }
    //主页展示的数据
    public Page<PostReplyCount> findAllPost(Integer pageNo) {
        //通过日期统计次数
        int count = postdao.countByDate();
        //创建page对象
        Page<PostReplyCount> postReplyCountPage = new Page<>(count,pageNo);
        List<PostReplyCount> postReplyCounts = postdao.findAllPostReply(postReplyCountPage);
        postReplyCountPage.setItems(postReplyCounts);
        return postReplyCountPage;
    }
}
