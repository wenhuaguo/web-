package com.kaishengit.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Acer on 2017/2/17.
 * 公司合同
 */
@Data
public class CompanyContract implements Serializable {
    private Integer id;
    private String rentCompany;
    private String companyTel;
    private String address;
    private String rentTime;
    private String backTime;
    private Integer num;
    private String legalRepresent;
    private String legalTel;
    private String fax;
    private String cardNum;
    private Float totalPrice;
    private Float preCost;
    private Float lastCost;
    private Timestamp createTime;
    private String createUser;
    private Integer rentDays;
    //流水号
    private String serialNum;
    private String state;


}
