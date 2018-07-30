package com.bupt.entity;

import javax.persistence.Entity;

/**
 * Created by Stadpole on 2018/3/28.
 */
public class Location {
    private String M;
    private String O;
    private String lat;
    private String lng;

    public String getM() {
        return M;
    }

    public void setM(String m) {
        M = m;
    }

    public String getO() {
        return O;
    }

    public void setO(String o) {
        O = o;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
