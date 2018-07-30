package com.bupt.common.enums;

/**
 * Created by Xtj on 2017/7/3.
 */
public enum StatusEnum {
    OPEN(1,"可用"),
    CLOSE (2,"不可用");

    private int value;
    private  String name;

    StatusEnum(final int value, final String name){
        this.setValue(value);
        this.setName(name);
    }
    // 根据index获取enum对象
    public static StatusEnum findByIndex(int index) {
        for (StatusEnum positionEnum : StatusEnum.values()) {
            if (index == positionEnum.getValue()) {
                return positionEnum;
            }
        }
        return null;
    }
    // 根据value获取name
    public static String  findByValue(int value) {
        for (StatusEnum positionEnum : StatusEnum.values()) {
            if (value == positionEnum.getValue()) {
                return positionEnum.getName();
            }
        }
        return null;
    }
    // 根据name获取value
    public static int  findByName(String name) {
        for (StatusEnum positionEnum : StatusEnum.values()) {
            if (name == positionEnum.getName()) {
                return positionEnum.getValue();
            }
        }
        return 0;
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
