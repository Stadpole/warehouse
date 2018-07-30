package com.bupt.entity;

import java.sql.Timestamp;


/**
 * Created by Stadpole on 2018/4/20.
 */
public class Examine {
    private String ckId;
    private String spId;
    private Double rkCount;
    private Double ckCount;

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

    public Double getRkCount() {
        return rkCount;
    }

    public void setRkCount(Double rkCount) {
        this.rkCount = rkCount;
    }

    public Double getCkCount() {
        return ckCount;
    }

    public void setCkCount(Double ckCount) {
        this.ckCount = ckCount;
    }
}
