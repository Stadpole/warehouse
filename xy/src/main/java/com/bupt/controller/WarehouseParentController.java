package com.bupt.controller;
import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.NumberUtills;
import com.bupt.entity.Warehouse;
import com.bupt.entity.WarehouseParent;
import com.bupt.service.LocationDistrictService;
import com.bupt.service.WarehouseParentService;
import com.bupt.service.WarehouseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/warehouseParent")
public class WarehouseParentController extends BaseCommonController {
    @Autowired
    private WarehouseParentService warehouseParentService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private LocationDistrictService locationDistrictService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @RequiresPermissions("warehouse:manage")//权限管理;
    public String save(@RequestBody WarehouseParent entity) {
       try{
           WarehouseParent warehouseParent=warehouseParentService.findByLocationName(entity.getName());
           if(warehouseParent==null){
               entity.setParentId(locationDistrictService.findByDistrict(entity.getDistrict()).getId());
               entity.setCount(0.0);
               warehouseParentService.save(entity);
               double num=0.0;
               List<Warehouse> list=entity.getSubDistricts();
               if(list!=null){
                   for (Warehouse warehouse : list) {
                       warehouse.setCkId(NumberUtills.getNumber());
                       warehouse.setParentId(entity.getId());
                       warehouse.setCkStatus(0);
                       warehouse.setCount(0.0);
                       warehouseService.save(warehouse);
                       num++;
                   }
               }
               entity.setCount(num);
               warehouseParentService.save(entity);
           }
           else if(warehouseParent!=null){
               double num=warehouseParent.getCount();
               List<Warehouse> list=entity.getSubDistricts();
               if(list!=null) {
                   for (Warehouse warehouse:list) {
                       num++;
                       warehouse.setCkId(NumberUtills.getNumber());
                       warehouse.setParentId(warehouseParent.getId());
                       warehouse.setCkStatus(0);
                       warehouse.setCount(0.0);
                       warehouseService.save(warehouse);
                   }
               }
               warehouseParent.setCount(num);
               warehouseParentService.save(warehouseParent);
           }
           return sendSuccessMessage();

       }catch (NullPointerException e){
           return sendFailMessage();
       }
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @RequiresPermissions("warehouse:manage")//权限管理;
    public String update(@RequestBody WarehouseParent entity) {
        if (entity.getId()!=null) {
            WarehouseParent warehouseParent = warehouseParentService.findOne(entity.getId());
            BeanUtills.copyProperties(entity, warehouseParent);
            warehouseParentService.save(warehouseParent);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @RequiresPermissions("warehouse:manage")//权限管理;
    public String deleteById(Integer id) {
        try{
            if(id!=null){
                List<Warehouse> warehouseList=warehouseService.findByParentId(id);
                if(warehouseList!=null) {
                    for (Warehouse warehouse : warehouseList) {
                        warehouseService.deleteByCkId(warehouse.getCkId());
                    }
                }
                warehouseParentService.deleteById(id);
                return sendSuccessMessage();
            }
            return sendFailMessage();
        }catch (Exception e){
            return sendFailMessage();
        }
    }
    /**
     * 盘点  ：需要查WarehouseIn和WarehouseOut  对每一类物品的出库入库做一个统计
     * */

    @RequestMapping("/page")
    @RequiresPermissions("warehouse:view")//权限管理;
    public String page(WarehouseParent entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<WarehouseParent> pageEntity = new PageEntity<>(start, size, page);
        warehouseParentService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(WarehouseParent entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        return parameterMap;
    }
}

