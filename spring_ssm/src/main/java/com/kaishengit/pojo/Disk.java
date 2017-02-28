package com.kaishengit.pojo;

import lombok.Data;

/**
 * Created by Acer on 2017/2/21.
 * 公司网盘
 */
@Data
public class Disk {
    //两个常量用来标示文件类型是文件还是文件夹
    public static final String FILE="file";
    public static final String DIRECTORY = "directory";
    private Integer id;
    private String sourceName;
    private String name;
    private String createUser;
    private String createTime;
    private String docType;
    private String size;
    private Integer fid;
}
