package com.kaishengit.entity;

import java.sql.Timestamp;

/**
 * Created by Acer on 2016/12/27.
 */
public class Thank {
    private Integer userid;
    private Integer postid;
    private Timestamp createtime;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
}
