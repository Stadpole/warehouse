package com.bupt.common.enums;

/**
 * Created by Xtj on 2017/7/3.
 */
public enum BuildingEnum {
    ONE(1,"1"),
    TWO (2,"2"),
    THREE (3,"3"),
    FOUR (4,"4"),
    FIRE (5,"5"),
    SIX (6,"6"),
    SEVEN (7,"7"),
    EIGHT (8,"8"),
    NINE (9,"9"),
    TEN(10,"10");

    private int value;
    private  String name;

    BuildingEnum(final int value, final String name){
        this.setValue(value);
        this.setName(name);
    }
    // 根据index获取enum对象
    public static BuildingEnum findByIndex(int index) {
        for (BuildingEnum positionEnum : BuildingEnum.values()) {
            if (index == positionEnum.getValue()) {
                return positionEnum;
            }
        }
        return null;
    }
    // 根据value获取name
    public static String  findByValue(int value) {
        for (BuildingEnum positionEnum : BuildingEnum.values()) {
            if (value == positionEnum.getValue()) {
                return positionEnum.getName();
            }
        }
        return null;
    }
    // 根据name获取value
    public static int  findByName(String name) {
        for (BuildingEnum positionEnum : BuildingEnum.values()) {
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
