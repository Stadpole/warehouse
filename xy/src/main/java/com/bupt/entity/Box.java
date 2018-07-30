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
@Table(name="t_box")
public class Box {
    @Expose
    private String id;
    @Expose
    private String hgId;
    private String ckId;
    @Expose
    private Integer hgSizeId;
    @Expose
    private Integer hgApplyStatus;
    @Expose
    private Integer hgStatus;
    @Expose
    private String hgName;
    private Date createTime;
    private Date lastUpdate;
    @Transient
    @Expose
    private String hgSizeName;
    @Transient
    @Expose
    private Integer isPossess;
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
    @Column(name = "HG_ID")
    public String getHgId() {
        return hgId;
    }

    public void setHgId(String hgId) {
        this.hgId = hgId;
    }
    @Basic
    @Column(name = "CK_ID")
    public String getCkId() {
        return ckId;
    }

    public void setCkId(String ckId) {
        this.ckId = ckId;
    }

    @Basic
    @Column(name = "HG_SIZE_ID")
    public Integer getHgSizeId() {
        return hgSizeId;
    }

    public void setHgSizeId(Integer hgSizeId) {
        this.hgSizeId = hgSizeId;
    }

    @Basic
    @Column(name = "HG_STATUS")
    public Integer getHgStatus() {
        return hgStatus;
    }

    public void setHgStatus(Integer hgStatus) {
        this.hgStatus = hgStatus;
    }
    @Basic
    @Column(name = "HG_NAME")
    public String getHgName() {
        return hgName;
    }

    public void setHgName(String hgName) {
        this.hgName = hgName;
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
    @Transient
    public String getHgSizeName() {
        return hgSizeName;
    }

    public void setHgSizeName(String hgSizeName) {
        this.hgSizeName = hgSizeName;
    }
    @Transient
    public Integer getIsPossess() {
        return isPossess;
    }
    public void setIsPossess(Integer isPossess) {
        this.isPossess = isPossess;
    }
    @Basic
    @Column(name = "HG_APPLY_STATUS")
    public Integer getHgApplyStatus() {
        return hgApplyStatus;
    }

    public void setHgApplyStatus(Integer hgApplyStatus) {
        this.hgApplyStatus = hgApplyStatus;
    }
}
