package com.kaishengit.entity;

import java.sql.Timestamp;

/**
 * Created by Acer on 2016/12/15.
 */
public class User {
    public final static String DEFAULT_AVATAR_NAME = "default-avatar.jpg";
    public final static Integer UNACTIVE = 0;
    public final static Integer ACTIVE = 1;
    public final static Integer DISABLED = 2;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer id;
    private String avatar;
    private Integer state;
    private Timestamp createtime;

    public static String getDefaultAvatarName() {
        return DEFAULT_AVATAR_NAME;
    }

    public static Integer getUNACTIVE() {
        return UNACTIVE;
    }

    public static Integer getACTIVE() {
        return ACTIVE;
    }

    public static Integer getDISABLED() {
        return DISABLED;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
