package com.kaishengit.pojo;



import lombok.Data;

import java.io.Serializable;

/**
 * Created by Acer on 2017/2/18.
 */
@Data
public class LabourType implements Serializable {
    private Integer id;
    private String workType;
    private Float unitMoney;
    private Integer totalNum;
    private Integer currentNum;
}
