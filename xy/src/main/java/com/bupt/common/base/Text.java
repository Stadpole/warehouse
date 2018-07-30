package com.bupt.common.base;

/**
 * Created by bupt626 on 17-4-15.
 */
public class Text {
    private String value;
    private String name;

    public Text(String value,String name){
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
