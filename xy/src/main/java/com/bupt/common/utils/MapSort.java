package com.bupt.common.utils;

import java.util.*;

/**
 * Created by Stadpole on 2018/7/9.
 */
public class MapSort {
public static void  mapSort(Map<String,Double> map) {

    List<Map.Entry<String, Double>> wordMap = new ArrayList<Map.Entry<String, Double>>(map.entrySet());
    Collections.sort(wordMap, new Comparator<Map.Entry<String, Double>>() {//根据value排序
        public int compare(Map.Entry<String, Double> o1,
                           Map.Entry<String, Double> o2) {
            double result = o2.getValue() - o1.getValue();
            if (result > 0)
                return 1;
            else if (result == 0)
                return 0;
            else
                return -1;
        }
    });
}

}
