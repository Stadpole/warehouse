package com.bupt.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Stadpole on 2017/12/19.
 */
@Entity
public class WarehouseParent {
    @Id
    @GeneratedValue
    @Expose
    private Integer id;
    @Expose
    @Column(unique = true)
    private String name;//仓库所在位置总称
    private Double lng;//仓库经度
    private Double lat;//仓库纬度
    @Expose
    private Double count;
    @Expose
    private Integer parentId;
    @Transient
    private List<Warehouse> subDistricts;
    @Transient
    private String center;
    @Transient
    private String city;
    @Transient
    private String district;
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

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public List<Warehouse> getSubDistricts() {
        return subDistricts;
    }

    public void setSubDistricts(List<Warehouse> subDistricts) {
        this.subDistricts = subDistricts;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}