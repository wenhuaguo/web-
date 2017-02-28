package com.kaishengit.pojo;

import lombok.Data;

/**
 * Created by Acer on 2017/2/19.
 * 劳务详情
 */
@Data
public class LabourDetail {
    private Integer id;
    private String workType;
    private Integer rentNum;
    private Float unitMoney;
    private Float total;
    private Integer rentId;
}
