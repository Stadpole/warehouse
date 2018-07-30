package com.bupt.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Stadpole on 2017/12/12.
 */
@Entity
@Table(name="t_warehouse_out")
public class WarehouseOut {
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
    private Double outCount;
    @Expose
    private Integer outStatus;
    @Expose
    private String application;
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

    public Double getOutCount() {
        return outCount;
    }

    public void setOutCount(Double outCount) {
        this.outCount = outCount;
    }

    public Integer getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(Integer outStatus) {
        this.outStatus = outStatus;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
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

    public Double getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Double applyCount) {
        this.applyCount = applyCount;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
}
