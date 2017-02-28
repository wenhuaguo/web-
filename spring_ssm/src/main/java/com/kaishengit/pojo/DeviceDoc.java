package com.kaishengit.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Acer on 2017/2/17.
 * 上传合同文件列表
 */
@Data
public class DeviceDoc implements Serializable {
    private Integer id;
    private String newFileName;
    private String sourceFileName;
    private Integer rentId;
}
