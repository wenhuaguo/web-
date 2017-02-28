package com.kaishengit.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Acer on 2017/2/17.
 * 设备详情列表
 */
@Data
public class RentDeviceDetail implements Serializable {
    private Integer id;
    private String deviceName;
    private String unit;
    private Float rentPrice;
    private Integer num;
    private Integer rentId;
    private Float totalPrice;
}
