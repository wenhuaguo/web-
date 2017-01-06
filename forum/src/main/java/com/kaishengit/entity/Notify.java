package com.kaishengit.entity;

import java.sql.Timestamp;

/**
 * Created by Acer on 2016/12/26.
 */
public class Notify {
    private Integer id;
    private String content;
    private Timestamp createtime;
    private Timestamp readtime;
    private Integer state;
    private Integer userid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public static final Integer UNREAD_STATE = 0;
    public static final Integer READ_STATE = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getReadtime() {
        return readtime;
    }

    public void setReadtime(Timestamp readtime) {
        this.readtime = readtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public static Integer getUnreadState() {
        return UNREAD_STATE;
    }

    public static Integer getReadState() {
        return READ_STATE;
    }
}
