package com.bupt.common.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by Stadpole on 2018/7/9.
 */
class MapValueComparator implements Comparator<Map.Entry<String, String>> {

    @Override
    public int compare(Map.Entry<String, String> me1, Map.Entry<String, String> me2) {

        return me1.getValue().compareTo(me2.getValue());
    }
}