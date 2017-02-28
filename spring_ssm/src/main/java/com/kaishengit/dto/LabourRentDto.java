package com.kaishengit.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Acer on 2017/2/19.
 */
public class LabourRentDto implements Serializable{

    /**
     * labourArray : [{"id":"1","workType":"塔吊司机","unitMoney":"200","dispatchNum":"1","total":200}]
     * fileUpload : [{"sourceFile":"agp_heat.min.js.下载","newFile":"f0a0e058-977b-4857-bdce-2fe808c077b3.下载"}]
     * rentCompany : 1
     * address : 1
     * companyTel : 1
     * rentDay : 2017-02-19
     * backDay : 2017-02-23
     * dayNum : 4
     * legalRepresent : 1
     * legalTel : 1
     * idNum : 1
     */

    private String rentCompany;
    private String address;
    private String companyTel;
    private String rentDay;
    private String backDay;
    private Integer dayNum;
    private String legalRepresent;
    private String legalTel;
    private String idNum;
    private List<LabourArrayBean> labourArray;
    private List<FileUploadBean> fileUpload;

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

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
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

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
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

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public List<LabourArrayBean> getLabourArray() {
        return labourArray;
    }

    public void setLabourArray(List<LabourArrayBean> labourArray) {
        this.labourArray = labourArray;
    }

    public List<FileUploadBean> getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(List<FileUploadBean> fileUpload) {
        this.fileUpload = fileUpload;
    }

    public static class LabourArrayBean {
        /**
         * id : 1
         * workType : 塔吊司机
         * unitMoney : 200
         * dispatchNum : 1
         * total : 200
         */

        private Integer id;
        private String workType;
        private Float unitMoney;
        private Integer dispatchNum;
        private Float total;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getWorkType() {
            return workType;
        }

        public void setWorkType(String workType) {
            this.workType = workType;
        }

        public Float getUnitMoney() {
            return unitMoney;
        }

        public void setUnitMoney(Float unitMoney) {
            this.unitMoney = unitMoney;
        }

        public Integer getDispatchNum() {
            return dispatchNum;
        }

        public void setDispatchNum(Integer dispatchNum) {
            this.dispatchNum = dispatchNum;
        }

        public Float getTotal() {
            return total;
        }

        public void setTotal(Float total) {
            this.total = total;
        }
    }

    public static class FileUploadBean {
        /**
         * sourceFile : agp_heat.min.js.下载
         * newFile : f0a0e058-977b-4857-bdce-2fe808c077b3.下载
         */

        private String sourceFile;
        private String newFile;

        public String getSourceFile() {
            return sourceFile;
        }

        public void setSourceFile(String sourceFile) {
            this.sourceFile = sourceFile;
        }

        public String getNewFile() {
            return newFile;
        }

        public void setNewFile(String newFile) {
            this.newFile = newFile;
        }
    }
}
