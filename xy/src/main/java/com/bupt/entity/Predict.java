package com.bupt.entity;

import java.util.Date;

/**
 * Created by Stadpole on 2018/8/2.
 */
public class Predict {
    private String spId;
    private String spName;
    private String spBrand;
    private String spType;
    private String rkCk;
    private Double rkCount;
    private Date rkTime;
    private String outCk;
    private  Date outTime;
    private Double outCount;

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getSpBrand() {
        return spBrand;
    }

    public void setSpBrand(String spBrand) {
        this.spBrand = spBrand;
    }

    public String getSpType() {
        return spType;
    }

    public void setSpType(String spType) {
        this.spType = spType;
    }

    public String getRkCk() {
        return rkCk;
    }

    public void setRkCk(String rkCk) {
        this.rkCk = rkCk;
    }

    public Double getRkCount() {
        return rkCount;
    }

    public void setRkCount(Double rkCount) {
        this.rkCount = rkCount;
    }

    public Date getRkTime() {
        return rkTime;
    }

    public void setRkTime(Date rkTime) {
        this.rkTime = rkTime;
    }

    public String getOutCk() {
        return outCk;
    }

    public void setOutCk(String outCk) {
        this.outCk = outCk;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Double getOutCount() {
        return outCount;
    }

    public void setOutCount(Double outCount) {
        this.outCount = outCount;
    }
}
