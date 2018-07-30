package com.bupt.entity;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Stadpole on 2017/12/12.
 */
@Entity
public class Commodity {
    @Expose
    @Id
    @Column(updatable = false)
    private String spId;
    @Expose
    @Column(unique = true)
    private String spName;
    @Expose
    private String spBrand;
    @Expose
    private String spType;
    @Expose
    private String spAttribute;
    @Expose
    private String spSize;
    @Expose
    @Temporal(TemporalType.TIMESTAMP)//指定映射数据库中的日期事件类型
    @CreationTimestamp
    @Column(updatable = false )
    private Date createTime;
    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date lastUpdate;
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

    public String getSpAttribute() {
        return spAttribute;
    }

    public void setSpAttribute(String spAttribute) {
        this.spAttribute = spAttribute;
    }

    public String getSpSize() {
        return spSize;
    }

    public void setSpSize(String spSize) {
        this.spSize = spSize;
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
}
