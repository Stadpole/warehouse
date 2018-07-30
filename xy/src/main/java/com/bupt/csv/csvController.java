package com.bupt.csv;

import com.bupt.common.base.BaseCommonController;
import com.bupt.common.utils.ExportUtil;
import com.bupt.entity.Warehouse;
import com.bupt.service.WarehouseService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.security.validator.Validator;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bupt.common.utils.CsvUtils.AssembledData;

/**
 * Created by Stadpole on 2018/6/11.
 */

@RestController
@RequestMapping(value = "/csv")
public class csvController extends BaseCommonController{
    @Autowired
    private WarehouseService warehouseService;
    @RequestMapping(value = "", method = RequestMethod.GET)
    public  void run(){
        //querySql查询数据库方法

        List<Map<String, Object>> dataList=null;

        List<Warehouse> warehouses = warehouseService.findAll();// 查询到要导出的信息
        if (warehouses.size() == 0) {
            System.out.print("无数据导出");
        }
        dataList = new ArrayList<>();
        Map<String, Object> map = null;
        for (Warehouse warehouse : warehouses) {
            map = new HashMap<String, Object>();
            map.put("createDate", DateFormatUtils.format(warehouse.getCreateTime(), "yyyy/MM/dd HH:mm"));
            map.put("ckId", warehouse.getCkId());
            map.put("ckName", warehouse.getName());
            map.put("ckAddress", warehouse.getCkAddress());
            dataList.add(map);
        }
        AssembledData(dataList);
    }
}
