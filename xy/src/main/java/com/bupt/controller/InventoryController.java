package com.bupt.controller;

import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.entity.Commodity;
import com.bupt.entity.Inventory;
import com.bupt.service.CommodityService;
import com.bupt.service.InventoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/inventory")
public class InventoryController extends BaseCommonController {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@RequestBody Inventory entity) {

         if(entity!=null){
             Commodity commodity=commodityService.findBySpId(entity.getSpId());
             if(commodity!=null){
                 entity.setSpName(commodity.getSpName());
             }
             inventoryService.save(entity);
             return sendSuccessMessage();
         }
    return sendFailMessage();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody Inventory entity) {
        if (entity.getId()!=null) {
            Inventory inventory = inventoryService.findById(entity.getId());
            BeanUtills.copyProperties(entity, inventory);
            inventoryService.save(inventory);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable(value = "id") Integer id) {
        Inventory entity = inventoryService.findById(id);
        return sendMessage("true", "", entity, DateUtil.DATE);
    }

    @RequestMapping(value = "",method = RequestMethod.DELETE)
    public String deleteById(Integer id) {
        try{
            inventoryService.deleteById(id);
            return sendSuccessMessage();
        }catch(Exception e){
            return  sendFailMessage();
        }
    }

    @RequestMapping("/page")
    public String page(Inventory entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<Inventory> pageEntity = new PageEntity<>(start, size, page);
        inventoryService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(Inventory entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        return parameterMap;
    }
}

