package com.bupt.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Stadpole on 2017/12/12.
 */
@Entity
public class WarehouseIn {
    @Expose
    @Id
    @Column(updatable = false)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Expose
    private Integer yhId;
    @Expose
    private String ckId;
    @Expose
    private Integer ckManager;
    @Expose
    @Column(unique = true)
    private String hgId;
    @Expose
    private String spId;
    @Expose
    private Double rkCount;
    @Expose
    private Integer rkStatus;
    @Expose
    private String remark;
    @Expose
    @Temporal(TemporalType.TIMESTAMP)//指定映射数据库中的日期事件类型
    @CreationTimestamp
    @Column(updatable = false )
    private Date createTime;
    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date lastUpdate;
    @Expose
    @Transient
    private Double applyCount;
    @Expose
    @Transient
    private  Commodity commodity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYhId() {
        return yhId;
    }

    public void setYhId(Integer yhId) {
        this.yhId = yhId;
    }


    public String getCkId() {
        return ckId;
    }

    public void setCkId(String ckId) {
        this.ckId = ckId;
    }

    public Integer getCkManager() {
        return ckManager;
    }

    public void setCkManager(Integer ckManager) {
        this.ckManager = ckManager;
    }

    public String getHgId() {
        return hgId;
    }

    public void setHgId(String hgId) {
        this.hgId = hgId;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public Double getRkCount() {
        return rkCount;
    }

    public void setRkCount(Double rkCount) {
        this.rkCount = rkCount;
    }

    public Integer getRkStatus() {
        return rkStatus;
    }

    public void setRkStatus(Integer rkStatus) {
        this.rkStatus = rkStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Double getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Double applyCount) {
        this.applyCount = applyCount;
    }
}
