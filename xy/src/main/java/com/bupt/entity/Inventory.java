package com.bupt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Stadpole on 2017/12/12.
 */
@Entity
public class Inventory {
    @Id
    @GeneratedValue
    @Expose
    private Integer id;
    @Expose
    private Integer yhId;
    @Expose
    private String ckId;
    @Expose
    private String spId;
    @Expose
    private String spName;
    @Expose
    private String spType;
    @Expose
    private String unit;//单位
    @Expose
    private double rkCount;
    @Expose
    private double outCount;
    @Expose
    private double totalCount;//账面数量
    @Expose
    private double checkCount;//盘点数量
    @Expose
    private double dValue;    //盈亏数量
    @Expose
    @Temporal(TemporalType.TIMESTAMP)//指定映射数据库中的日期事件类型
    @CreationTimestamp
    @Column(updatable = false )
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date lastUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getSpType() {
        return spType;
    }

    public void setSpType(String spType) {
        this.spType = spType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getRkCount() {
        return rkCount;
    }

    public void setRkCount(double rkCount) {
        this.rkCount = rkCount;
    }

    public double getOutCount() {
        return outCount;
    }

    public void setOutCount(double outCount) {
        this.outCount = outCount;
    }

    public double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(double totalCount) {
        this.totalCount = totalCount;
    }

    public double getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(double checkCount) {
        this.checkCount = checkCount;
    }

    public double getdValue() {
        return dValue;
    }

    public void setdValue(double dValue) {
        this.dValue = dValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getYhId() {
        return yhId;
    }

    public void setYhId(Integer yhId) {
        this.yhId = yhId;
    }
}
