package com.bupt.controller;

import com.bupt.aop.OperationLogger;
import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.common.utils.NumberUtills;
import com.bupt.entity.Examine;
import com.bupt.entity.Warehouse;
import com.bupt.service.WarehouseService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/warehouse")
public class WarehouseController extends BaseCommonController {
    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @OperationLogger(moduleName = "仓库管理", option = "新增",operationDetail= "仓库信息")
    @RequiresPermissions("warehouse:manage")//权限管理;
    public String save(@RequestBody Warehouse entity) {
        entity.setCkId(NumberUtills.getNumber());
       try{
           warehouseService.save(entity);
       }catch (Exception e){
           return sendFailMessage();
       }
        return sendSuccessMessage();
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @OperationLogger(moduleName = "仓库管理", option = "更新",operationDetail= "仓库信息")
    @RequiresPermissions("warehouse:manage")//权限管理;
    public String update(@RequestBody Warehouse entity) {
        if ((StringUtils.isNotBlank(entity.getCkId()))) {
            Warehouse warehouse = warehouseService.findOne(entity.getCkId());
            BeanUtills.copyProperties(entity, warehouse);
            warehouseService.save(warehouse);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "仓库信息")
    @RequiresPermissions("warehouse:manage")//权限管理;
    public Double[] findOne(String ckId) {
        Warehouse entity = warehouseService.findOne(ckId);
        Double result[]=new Double[2];
        result[0]=entity.getLng();
        result[1]=entity.getLat();
        return result;
    }
    @RequestMapping("/pageByTime")
    @RequiresPermissions("warehouse:manage")//权限管理;
    public Page<Warehouse> findtime(int page,int size,Date start, Date end) {
        Sort sort = new Sort(Sort.Direction.DESC, "ckId");
        Pageable pageable = new PageRequest(page-1, size,sort);
        Page<Warehouse> warehouses=warehouseService.findByTime(start,end,pageable);
       return warehouses;
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @OperationLogger(moduleName = "仓库管理", option = "删除",operationDetail= "仓库信息")
    @RequiresPermissions("warehouse:manage")//权限管理;
    public String deleteById(String ckId) {
        warehouseService.deleteByCkId(ckId);
        return sendSuccessMessage();
    }
    /**
     * 盘点  ：需要查WarehouseIn和WarehouseOut  对每一类物品的出库入库做一个统计
     * */

    @RequestMapping("/page")
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "仓库列表")
    @RequiresPermissions("warehouse:view")//权限管理;
    public String page(Warehouse entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<Warehouse> pageEntity = new PageEntity<>(start, size, page);
        warehouseService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(Warehouse entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        return parameterMap;
    }
}

