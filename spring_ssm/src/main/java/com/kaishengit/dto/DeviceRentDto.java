package com.kaishengit.dto;

import java.util.List;

/**
 * Created by Acer on 2017/2/17.
 */
public class DeviceRentDto {


    /**
     * deviceArray : [{"id":"3","name":"钩机","unit":"吨","price":"300000","num":"1","total":300000},{"id":"5","name":"搅拌机","unit":"台","price":"5000","num":"1","total":5000}]
     * fileArray : [{"sourceFileName":"Change.java","newFileName":"fe3de701-e26f-4885-bbf0-e5cb2dfece3a.java"},{"sourceFileName":"Bianliang.java","newFileName":"26419ccb-71af-47ad-8d29-df94a050a607.java"}]
     * rentCompany : 天海
     * address : 天津
     * tel : 111111
     * rentDay : 17-02-18
     * backDay : 2017-02-24
     * totalDate : 6
     * legalRepresent : 张三
     * legalTel : 11111111111
     * cardNum : 111111111111111111
     * fax : 111111
     */

    private String rentCompany;
    private String address;
    private String tel;
    private String rentDay;
    private String backDay;
    private Integer totalDate;
    private String legalRepresent;
    private String legalTel;
    private String cardNum;
    private String fax;
    private List<DeviceArrayBean> deviceArray;
    private List<FileArrayBean> fileArray;

    public String getRentCompany() {
        return rentCompany;
    }

    public void setRentCompany(String rentCompany) {
        this.rentCompany = rentCompany;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRentDay() {
        return rentDay;
    }

    public void setRentDay(String rentDay) {
        this.rentDay = rentDay;
    }

    public String getBackDay() {
        return backDay;
    }

    public void setBackDay(String backDay) {
        this.backDay = backDay;
    }

    public Integer getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(Integer totalDate) {
        this.totalDate = totalDate;
    }

    public String getLegalRepresent() {
        return legalRepresent;
    }

    public void setLegalRepresent(String legalRepresent) {
        this.legalRepresent = legalRepresent;
    }

    public String getLegalTel() {
        return legalTel;
    }

    public void setLegalTel(String legalTel) {
        this.legalTel = legalTel;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public List<DeviceArrayBean> getDeviceArray() {
        return deviceArray;
    }

    public void setDeviceArray(List<DeviceArrayBean> deviceArray) {
        this.deviceArray = deviceArray;
    }

    public List<FileArrayBean> getFileArray() {
        return fileArray;
    }

    public void setFileArray(List<FileArrayBean> fileArray) {
        this.fileArray = fileArray;
    }

    public static class DeviceArrayBean {
        /**
         * id : 3
         * name : 钩机
         * unit : 吨
         * price : 300000
         * num : 1
         * total : 300000
         */

        private Integer id;
        private String name;
        private String unit;
        private Float price;
        private Integer num;
        private Float total;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Float getPrice() {
            return price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
            this.total = total;
        }
    }

    public static class FileArrayBean {
        /**
         * sourceFileName : Change.java
         * newFileName : fe3de701-e26f-4885-bbf0-e5cb2dfece3a.java
         */

        private String sourceFileName;
        private String newFileName;

        public String getSourceFileName() {
            return sourceFileName;
        }

        public void setSourceFileName(String sourceFileName) {
            this.sourceFileName = sourceFileName;
        }

        public String getNewFileName() {
            return newFileName;
        }

        public void setNewFileName(String newFileName) {
            this.newFileName = newFileName;
        }
    }
}
