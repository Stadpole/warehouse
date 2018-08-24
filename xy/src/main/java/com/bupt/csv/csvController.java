package com.bupt.csv;

import com.bupt.common.base.BaseCommonController;
import com.bupt.entity.Predict;
import com.bupt.entity.Warehouse;
import com.bupt.service.CommodityService;
import com.bupt.service.PredictService;
import com.bupt.service.WarehouseService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.bupt.common.utils.CsvUtils.AssembledData;

/**
 * Created by Stadpole on 2018/6/11.
 */

@RestController
@RequestMapping(value = "/csv")
public class csvController extends BaseCommonController{
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private PredictService predictService;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String run(){
        try {
            //querySql查询数据库方法
            List<Map<String, Object>> dataList = null;
            List<Warehouse> warehouses = warehouseService.findAll();// 查询到要导出的信息
            if (warehouses.size() == 0) {
                System.out.print("无数据导出");
            }
            dataList = new ArrayList<>();
            List<Predict> list = predictService.selectDetail();

            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("spId", list.get(i).getSpId());
                map.put("spName", list.get(i).getSpName());
                map.put("spBrand", list.get(i).getSpBrand());
                map.put("spType", list.get(i).getSpType());
                map.put("rkCk", list.get(i).getRkCk());
                map.put("outCk", list.get(i).getOutCk());
                map.put("rkCount", list.get(i).getRkCount());
                if (list.get(i).getRkTime() == null) {
                    map.put("rkTime", "0");
                } else {

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//24小时制
                    String dateString = simpleDateFormat.format(list.get(i).getRkTime());
                    Date date= simpleDateFormat.parse(dateString);
                    Double dateMin=(double)date.getTime()/1000000.0;
                    dateMin=dateMin/1000000.0;
                    map.put("rkTime",dateMin);
                }
                if (list.get(i).getOutTime() == null) {
                    map.put("outTime", "0");
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//24小时制
                    String dateString = simpleDateFormat.format(list.get(i).getOutTime());
                    Date date= simpleDateFormat.parse(dateString);
                    Double dateMin=(double)date.getTime()/1000000.0;
                    dateMin=dateMin/1000000.0;
                    map.put("outTime", dateMin );
                }
                map.put("outCount", list.get(i).getOutCount());
                dataList.add(map);
            }
            AssembledData(dataList);
            return sendSuccessMessage();
        }catch (Exception e){
           return sendFailMessage();
        }
    }
}
