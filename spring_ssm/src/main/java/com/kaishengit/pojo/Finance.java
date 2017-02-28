package com.kaishengit.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Acer on 2017/2/23.
 */
@Data
public class Finance implements Serializable {
    public static final String TYPE_IN = "收入";
    public static final String TYPE_OUT = "支出";
    public static final String STATE_NEW = "未确认";
    public static final String STATE_OK = "已确认";
    private Integer id;
    private String state;
    private String module;
    private String type;
    private Float money;
    private String remark;
    private String confirmUser;
    private String confirmDate;
    private String createUser;
    private String createDate;
    private String serialNum;
    private String moduleSerial;
}
