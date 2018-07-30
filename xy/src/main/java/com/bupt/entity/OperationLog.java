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
@Table(name="t_operation_log")
public class OperationLog {
    private String id;
    @Expose
    private Integer yhId;
    @Expose
    private String yhUserName;
    @Expose
    private String olType;
    @Expose
    private String olDetail;
    @Expose
    private String olModule;
    @Expose
    private String ip;
    @Expose
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
    @Column(name = "YH_USERNAME")

    public String getYhUserName() {
        return yhUserName;
    }

    public void setYhUserName(String yhUserName) {
        this.yhUserName = yhUserName;
    }
    @Basic
    @Column(name = "YH_ID")

    public Integer getYhId() {
        return yhId;
    }

    public void setYhId(Integer yhId) {
        this.yhId = yhId;
    }

    @Basic
    @Column(name = "OL_TYPE")
    public String getOlType() {
        return olType;
    }

    public void setOlType(String olType) {
        this.olType = olType;
    }
    @Basic
    @Column(name = "OL_DETAIL")
    public String getOlDetail() {
        return olDetail;
    }

    public void setOlDetail(String olDetail) {
        this.olDetail = olDetail;
    }
    @Basic
    @Column(name = "OL_MODULE")
    public String getOlModule() {
        return olModule;
    }

    public void setOlModule(String olModule) {
        this.olModule = olModule;
    }

    @Basic
    @Column(name = "OL_IP")

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
