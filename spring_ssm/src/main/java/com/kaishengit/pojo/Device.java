package com.kaishengit.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Acer on 2017/1/18.
 * lombok是一个通过注解的形式消除必须有但是显得很臃肿的Java特别是对POJO代码避免写set和get方法以及toString等方法的框架
 */
@Data
public class Device implements Serializable {
    private Integer id;
    private String name;
    private String unit;
    private Integer totalNum;
    private Integer currentNum;
    private Float price;
    private Timestamp createTime;
    private Timestamp modifyTime;
}
