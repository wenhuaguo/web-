package com.kaishengit.entity;

import org.joda.time.DateTime;

import java.sql.Timestamp;

/**
 * Created by Acer on 2016/12/20.
 */
public class Post {
    private Integer id;
    private String title;
    private String content;
    private Timestamp createtime;
    private Integer clicknum;
    private Integer savenum;
    private Integer thanknum;
    private Integer userid;
    private Integer nodeid;
    private Timestamp lastreplytime;
    private Integer replynum;


    public Integer getReplynum() {
        return replynum;
    }

    public void setReplynum(Integer replynum) {
        this.replynum = replynum;
    }
    //为了将node对象和user对象的数据传递过去我们想出了将两个对象变为Post类的两个属性进行设置
    private User user;
    private Node node;

    public Timestamp getLastreplytime() {
        return lastreplytime;
    }

    public void setLastreplytime(Timestamp lastreplytime) {
        this.lastreplytime = lastreplytime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Integer getClicknum() {
        return clicknum;
    }

    public void setClicknum(Integer clicknum) {
        this.clicknum = clicknum;
    }

    public Integer getSavenum() {
        return savenum;
    }

    public void setSavenum(Integer savenum) {
        this.savenum = savenum;
    }

    public Integer getThanknum() {
        return thanknum;
    }

    public void setThanknum(Integer thanknum) {
        this.thanknum = thanknum;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getNodeid() {
        return nodeid;
    }

    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }
    //判断是否允许帖子被修改，拿修改的时间和在创建多长时间内进行比较如果在时间范围内那么允许修改，如果超过则不允许修改
    public boolean isEdit(){
        DateTime dateTime = new DateTime(getCreatetime());
        if(dateTime.plusMinutes(30).isAfterNow() && getReplynum() == 0){
            return true;
        }else {
            return false;
        }
    }

}
