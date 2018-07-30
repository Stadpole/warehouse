package com.bupt.common.utils;

/**
 * Created by Stadpole on 2018/5/24.
 */
import com.bupt.entity.Warehouse;
import com.bupt.service.WarehouseService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 *
 * CSV文件导出工具类
 */

public class CsvUtils {
    static  String out_put_Path="E:\\out_put";
    /**
     * CSV文件生成方法
     *
     * @param head 头部标题
     * @param dataList 内容
     * @param outPutPath 文件路径
     * @param filename 文件名称
     * @return
     */
    public static File createCSVFile(List<Object> head,List<List<Object>> dataList, String outPutPath, String filename) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            //文件储存位置
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
            // 写入文件头部
            writeRow(head, csvWtriter);
            // 写入文件内容
            for (List<Object> row : dataList) {
                writeRow(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据方法
     *
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter)
            throws IOException {
        // 写入文件头部
        for (Object data : row) {
//          StringBuffer sb = new StringBuffer();
            String rowStr="";
//          rowStr= sb.append("\"").append(data).append("\",").toString();
            if(data.toString().contains("\"")){
                rowStr="\""+data.toString().replaceAll("\"","\"\"")+"\",";
            }else{
                rowStr="\""+data.toString()+"\",";
            }
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
    /**
     * 拼装数据方法
     * @param retList 数据库查询结果集合
     */
    public static void AssembledData(List<Map<String,Object>> retList) {
        if(retList==null||retList.size()==0){
            //
        }else{
            List<Object> head=new ArrayList<Object>();
            List<List<Object>> dataList=new ArrayList<List<Object>>();
            for (Map<String, Object> map : retList) {
                List<Object> date=new ArrayList<Object>();
                for(Object key:map.keySet()){
                    date.add(map.get(key));
                }
                dataList.add(date);
            }
            for(Object ob:retList.get(0).keySet()){
                head.add(ob);
            }
            System.out.println(head);
            System.out.println(dataList);
            createCSVFile(head,dataList,out_put_Path,"test");
        }

    }
}