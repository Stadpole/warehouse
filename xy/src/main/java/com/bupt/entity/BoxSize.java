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
@Table(name="t_box_size")
public class BoxSize {
   @Expose
    private Integer hgSizeId;
    @Expose
    private String hgSize;
    @Expose
    private Date createTime;
    @Expose
    private Date lastUpdate;
    @Id
    @Column(name = "HG_SIZE_ID", updatable = false)
    public Integer getHgSizeId() {
        return hgSizeId;
    }
    public void setHgSizeId(Integer hgSizeId) {
        this.hgSizeId = hgSizeId;
    }

    @Basic
    @Column(name = "HG_SIZE")

    public String getHgSize() {
        return hgSize;
    }

    public void setHgSize(String hgSize) {
        this.hgSize = hgSize;
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
