package com.bupt.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Stadpole on 2017/12/19.
 */
@Entity
public class LocationParent {
    @Id
    @GeneratedValue
    @Expose
    private Integer id;
    @Expose
    @Column(unique = true)
    private String name;
    @Expose
    private Double lng;
    @Expose
    private Double lat;
    @Expose
    private Double count;
    @Transient
    private List<LocationDistrict> subDistricts;
    @Transient
    private String center;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LocationDistrict> getSubDistricts() {
        return subDistricts;
    }

    public void setSubDistricts(List<LocationDistrict> subDistricts) {
        this.subDistricts = subDistricts;
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

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }
}