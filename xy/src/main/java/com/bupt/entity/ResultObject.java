package com.bupt.entity;

import java.util.Date;

/**
 * Created by Stadpole on 2018/6/4.
 */
public class ResultObject {
    private String ckId;
    private String ckName;
    private String spId;
    private String spName;
    private String spType;
    private Double rkCount;
    private Double outCount;
    private String unit;
    private double totalCount;


    public String getCkName() {
        return ckName;
    }

    public void setCkName(String ckName) {
        this.ckName = ckName;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public Double getRkCount() {
        return rkCount;
    }

    public void setRkCount(Double rkCount) {
        this.rkCount = rkCount;
    }

    public Double getOutCount() {
        return outCount;
    }

    public void setOutCount(Double outCount) {
        this.outCount = outCount;
    }

    public String getSpType() {
        return spType;
    }

    public void setSpType(String spType) {
        this.spType = spType;
    }

    public String getCkId() {
        return ckId;
    }

    public void setCkId(String ckId) {
        this.ckId = ckId;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(double totalCount) {
        this.totalCount = totalCount;
    }
}
