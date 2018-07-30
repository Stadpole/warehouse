package com.bupt.controller;

import com.bupt.aop.OperationLogger;
import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.entity.Box;
import com.bupt.entity.Warehouse;
import com.bupt.entity.WarehouseApply;
import com.bupt.service.BoxService;
import com.bupt.service.CommodityService;
import com.bupt.service.WarehouseApplyService;
import com.bupt.service.WarehouseService;
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
@RequestMapping(value = "/warehouseApply")
public class WarehouseApplyController extends BaseCommonController {
    @Autowired
    private WarehouseApplyService warehouseApplyService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private BoxService boxService;
    @Autowired
    private WarehouseService warehouseService;

    @RequiresPermissions("warehouse:add")//权限管理;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @OperationLogger(moduleName = "仓库管理", option = "新增",operationDetail= "入库单")
    public String save(@RequestBody WarehouseApply entity) {
        try{
            if(entity!=null){
                Warehouse warehouse=warehouseService.findByCkId(entity.getCkId());
                entity.setCkManager(warehouse.getCkManager());
                entity.setApplyStatus(0);
                entity.setInStatus(0);
                warehouseApplyService.save(entity);
                return sendSuccessMessage();
                }
                else{
                return sendFailMessage();
            }
        }catch (Exception e){
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "/scheduling", method = RequestMethod.POST)
    @OperationLogger(moduleName = "仓库管理", option = "新增",operationDetail= "入库单")
    public String saveList(@RequestBody List<WarehouseApply> list) {
        try{
            for(WarehouseApply entity:list){
            if(entity!=null) {
                Warehouse warehouse = warehouseService.findByCkId(entity.getCkId());
                entity.setCkManager(warehouse.getCkManager());
                entity.setApplyStatus(0);
                entity.setInStatus(0);
                List<Box> boxes=boxService.findByCkId(entity.getCkId());
                //此处需要分配货柜，size0 5 1 10 2 20
                Map<String,Double> resultMap=warehouseApplyService.schedulingHg(entity.getCkId(),entity.getApplyCount());
                Iterator<String> iterator=resultMap.keySet().iterator();
                while(iterator.hasNext()){
                    String hgId=iterator.next();
                    Double count=resultMap.get(hgId);
                    entity.setHgId(hgId);
                    entity.setApplyCount(count);
                    warehouseApplyService.save(entity);
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
    @OperationLogger(moduleName = "仓库管理", option = "更新",operationDetail= "入库单申请")
    public String update(@RequestBody WarehouseApply entity) {
        if ((StringUtils.isNotBlank(entity.getId()))) {
            WarehouseApply warehouseApply = warehouseApplyService.findById(entity.getId());
            BeanUtills.copyProperties(entity, warehouseApply);
            warehouseApplyService.save(warehouseApply);
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
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "入库单")
    public String findOne(String HgId) {
        WarehouseApply entity = warehouseApplyService.findByHgId(HgId);
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
    @OperationLogger(moduleName = "仓库管理", option = "删除",operationDetail= "入库单申请")
    @RequiresPermissions("warehouse:manage")//权限管理;
    public String deleteById(String id) {
        warehouseApplyService.deleteById(id);
        return sendSuccessMessage();
    }
    //管理员按照manageID查看自己仓库的申请单
    @RequestMapping("/pageByManage")
    @RequiresPermissions("common:get")//权限管理，只有管理员可以查看;
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "入库申请表")
    public String pageByManage(WarehouseApply entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<WarehouseApply> pageEntity = new PageEntity<>(start, size, page);
        warehouseApplyService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }
    //申请用户的人查看自己的申请单，按照用户ID
    @RequestMapping("/page")
    @RequiresPermissions("common:get")//权限管理;
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "入库列表")
    public String page(WarehouseApply entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<WarehouseApply> pageEntity = new PageEntity<>(start, size, page);
        warehouseApplyService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }
    private Map<String, Object> buildParameter(WarehouseApply entity) {
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
        if (entity.getInStatus()!=null){
            parameterMap.put("inStatus", entity.getInStatus());
        }
        return parameterMap;
    }
}

