package com.bupt.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Stadpole on 2017/12/12.
 */
@Entity
public class PurchaseList {
    private String id;
    private String cgId;
    private String spId;
    private String spUnit;
    private Double spCount;
    private Double spPrice;
    private Double spTotalPrice;
    private String spRemark;
    private Date createTime;
    private Date lastUpdate;
    @Id
    @Column(name = "ID", updatable = false)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "CG_ID")
    public String getCgId() {
        return cgId;
    }

    public void setCgId(String cgId) {
        this.cgId = cgId;
    }
    @Basic
    @Column(name = "SP_ID")
    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }
    @Basic
    @Column(name = "SP_UNIT")
    public String getSpUnit() {
        return spUnit;
    }

    public void setSpUnit(String spUnit) {
        this.spUnit = spUnit;
    }
    @Basic
    @Column(name = "SP_COUNT")
    public Double getSpCount() {
        return spCount;
    }

    public void setSpCount(Double spCount) {
        this.spCount = spCount;
    }
    @Basic
    @Column(name = "SP_PRICE")
    public Double getSpPrice() {
        return spPrice;
    }

    public void setSpPrice(Double spPrice) {
        this.spPrice = spPrice;
    }
    @Basic
    @Column(name = "SP_TOTAL_PRICE")
    public Double getSpTotalPrice() {
        return spTotalPrice;
    }

    public void setSpTotalPrice(Double spTotalPrice) {
        this.spTotalPrice = spTotalPrice;
    }
    @Basic
    @Column(name = "SP_REMARK")
    public String getSpRemark() {
        return spRemark;
    }

    public void setSpRemark(String spRemark) {
        this.spRemark = spRemark;
    }

    @Temporal(TemporalType.TIMESTAMP)//指定映射数据库中的日期事件类型
    @CreationTimestamp
    @Column(name= "CREATE_TIME", updatable = false )
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name= "LAST_UPDATE")
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
