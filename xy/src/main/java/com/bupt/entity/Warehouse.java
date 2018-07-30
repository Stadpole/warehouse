package com.bupt.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by Stadpole on 2017/12/8.
 */
@Entity
public class Warehouse {
    @Id
    @Expose
    @Column(updatable = false)
    private String ckId;  //仓库编号
    @Column(unique =true)
    @Expose
    private String name;//仓库名称
    @Expose
    private Integer ckManager;//仓库管理员Id
    @Expose
    private Integer ckBuilder;//仓库创建者Id
    @Expose
    private Integer parentId;//
    @Expose
    private Double lng;//仓库经度
    @Expose
    private Double lat;//仓库纬度
    @Column(unique =true)
    @Expose
    private String ckAddress;//仓库地址
    @Expose
    private String floor;
    @Expose
    private String room;
    @Expose
    private Integer ckStatus;//仓库状态
    @Expose
    private String ckAttribute;//仓库属性
    @Expose
    private Double count;//仓库货柜总数
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
    //游离态
    @Transient
    private String ckStatusName;

    @Transient
    private Double[] Location;
    @Expose
    @Transient
    private String center;
    public String getCkId() {
        return ckId;
    }

    public void setCkId(String ckId) {
        this.ckId = ckId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCkManager() {
        return ckManager;
    }

    public void setCkManager(Integer ckManager) {
        this.ckManager = ckManager;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }


    public Integer getCkStatus() {
        return ckStatus;
    }

    public void setCkStatus(Integer ckStatus) {
        this.ckStatus = ckStatus;
    }

    public String getCkAttribute() {
        return ckAttribute;
    }

    public void setCkAttribute(String ckAttribute) {
        this.ckAttribute = ckAttribute;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
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
    public String getCkAddress() {
        return ckAddress;
    }

    public void setCkAddress(String ckAddress) {
        this.ckAddress = ckAddress;
    }
    public String getCkStatusName() {
        return ckStatusName;
    }

    public void setCkStatusName(String ckStatusName) {
        this.ckStatusName = ckStatusName;
    }


    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCkBuilder() {
        return ckBuilder;
    }

    public void setCkBuilder(Integer ckBuilder) {
        this.ckBuilder = ckBuilder;
    }

    public Double[] getLocation() {
        return Location;
    }

    public void setLocation(Double[] location) {
        Location = location;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
