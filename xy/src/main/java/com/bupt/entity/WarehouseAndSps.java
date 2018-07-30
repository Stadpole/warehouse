package com.bupt.entity;

import java.util.List;

/**
 * Created by Stadpole on 2018/5/2.
 */
public class WarehouseAndSps {
    private String value;
    private String label;
    private List<CommoditySelect> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CommoditySelect> getChildren() {
        return children;
    }

    public void setChildren(List<CommoditySelect> children) {
        this.children = children;
    }
}
