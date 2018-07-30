package com.bupt.controller;

import com.bupt.aop.OperationLogger;
import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.common.utils.EntityCopy;
import com.bupt.entity.Box;
import com.bupt.entity.Warehouse;
import com.bupt.entity.WarehouseApply;
import com.bupt.entity.WarehouseOutApply;
import com.bupt.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/warehouseOutApply")
public class WarehouseOutApplyController extends BaseCommonController {
    @Autowired
    private WarehouseOutApplyService warehouseOutApplyService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private BoxService boxService;
    @Autowired
    private WarehouseService warehouseService;

    @RequiresPermissions("warehouse:add")//权限管理;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @OperationLogger(moduleName = "仓库管理", option = "新增",operationDetail= "出库单")
    public String save(@RequestBody WarehouseOutApply entity) {
        try{
            if(entity!=null){
                Warehouse warehouse=warehouseService.findByCkId(entity.getCkId());
                entity.setCkManager(warehouse.getCkManager());
                entity.setApplyStatus(0);
                entity.setOutStatus(0);
                warehouseOutApplyService.save(entity);
                return sendSuccessMessage();
                }
                else{
                return sendFailMessage();
            }
        }catch (Exception e){
            return sendFailMessage();
        }
    }
    @RequiresPermissions("warehouse:add")//权限管理;
    @RequestMapping(value = "/scheduling", method = RequestMethod.POST)
    @OperationLogger(moduleName = "仓库管理", option = "新增",operationDetail= "出库单")
    public String save(@RequestBody List<WarehouseOutApply> list) {
        try {
            for (WarehouseOutApply entity : list) {
                if (entity != null) {
                    Warehouse warehouse = warehouseService.findByCkId(entity.getCkId());
                    entity.setCkManager(warehouse.getCkManager());
                    entity.setApplyStatus(0);
                    entity.setOutStatus(0);
                    List<Box> boxes = boxService.findByCkId(entity.getCkId());
                    //接下来分配货柜
                    Map<String, Double> resultMap = warehouseOutApplyService.schedulingHg(entity.getCkId(),entity.getSpId(), entity.getApplyCount());
                    Iterator<String> iterator = resultMap.keySet().iterator();
                    while (iterator.hasNext()) {
                        WarehouseOutApply warehouseOutApply=new WarehouseOutApply();
                        EntityCopy.Copy(entity,warehouseOutApply);
                        String hgId = iterator.next();
                        Double count = resultMap.get(hgId);
                        warehouseOutApply.setHgId(hgId);
                        warehouseOutApply.setApplyCount(count);
                        warehouseOutApplyService.save(warehouseOutApply);
                    }

                }

            }
            return sendSuccessMessage();
        }catch (Exception e){
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @RequiresPermissions("warehouse:add")//权限管理;
    @OperationLogger(moduleName = "仓库管理", option = "更新",operationDetail= "出库单申请")
    public String update(@RequestBody WarehouseOutApply entity) {
        if ((StringUtils.isNotBlank(entity.getId()))) {
            WarehouseOutApply warehouseOutApply = warehouseOutApplyService.findById(entity.getId());
            BeanUtills.copyProperties(entity, warehouseOutApply);
            warehouseOutApplyService.save(warehouseOutApply);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }
/**
 * 传入货柜ID
 * */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @RequiresPermissions("common:get")//权限管理;
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "出库单")
    public String findOne(String HgId) {
        WarehouseOutApply entity = warehouseOutApplyService.findByHgId(HgId);
        try {
            if (entity != null) {
                entity.setCommodity(commodityService.findById(entity.getSpId()));
            }
            return sendMessage("true", "", entity, DateUtil.DATE);
        }catch (Exception e){
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @OperationLogger(moduleName = "仓库管理", option = "删除",operationDetail= "出库单申请")
    @RequiresPermissions("warehouse:manage")//权限管理;
    public String deleteById(String id) {
        warehouseOutApplyService.deleteById(id);
        return sendSuccessMessage();
    }
    //管理员按照manageID查看自己仓库的申请单
    @RequestMapping("/pageByManage")
    @RequiresPermissions("common:get")//权限管理，只有管理员可以查看;
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "出库申请表")
    public String pageByManage(WarehouseOutApply entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<WarehouseOutApply> pageEntity = new PageEntity<>(start, size, page);
        warehouseOutApplyService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }
    //申请用户的人查看自己的申请单，按照用户ID
    @RequestMapping("/page")
    @RequiresPermissions("common:get")//权限管理;
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "出库列表")
    public String page(WarehouseOutApply entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<WarehouseOutApply> pageEntity = new PageEntity<>(start, size, page);
        warehouseOutApplyService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }
    private Map<String, Object> buildParameter(WarehouseOutApply entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        if (entity.getYhId()!=null){
            parameterMap.put("yhId", entity.getYhId());
        }
        if (entity.getCkManager()!=null){
            parameterMap.put("ckManager", entity.getCkManager());
        }
        if (entity.getApplyStatus()!=null){
            parameterMap.put("applyStatus", entity.getApplyStatus());
        }
        if (entity.getOutStatus()!=null){
            parameterMap.put("outStatus", entity.getOutStatus());
        }
        return parameterMap;
    }
}

