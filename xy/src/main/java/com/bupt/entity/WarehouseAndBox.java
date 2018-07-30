package com.bupt.entity;

import java.util.List;

/**
 * Created by Stadpole on 2018/5/2.
 */
public class WarehouseAndBox {
    private String value;
    private String label;
    private List<BoxSmall> children;

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

    public List<BoxSmall> getChildren() {
        return children;
    }

    public void setChildren(List<BoxSmall> children) {
        this.children = children;
    }
}
