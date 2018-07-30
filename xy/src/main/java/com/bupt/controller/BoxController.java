package com.bupt.controller;

import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.common.utils.NumberUtills;
import com.bupt.entity.Box;
import com.bupt.entity.Warehouse;
//import com.bupt.rabbitmq.send.GetInfos;
//import com.bupt.repository.BoxSizeRepository;
import com.bupt.service.BoxService;
//import com.bupt.service.BoxSizeService;
import com.bupt.service.WarehouseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/box")
public class BoxController extends BaseCommonController {
    @Autowired
    private BoxService boxService;
    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@RequestBody Box entity) {
            entity.setHgId(NumberUtills.getNumber());
            Warehouse warehouse=warehouseService.findOne(entity.getCkId());
            Double num=warehouse.getCount();
            if(num!=null) {
                warehouse.setCount(num + 1);
            }else{
                warehouse.setCount(0.0);
            }
            warehouseService.save(warehouse);
           entity.setHgStatus(0);
            boxService.save(entity);
        return sendSuccessMessage();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody Box entity) {
        if ((StringUtils.isNotBlank(entity.getId()))) {
            Box box = boxService.findById(entity.getId());
            BeanUtills.copyProperties(entity, box);
            boxService.save(box);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable(value = "id") String id) {
        Box entity = boxService.findById(id);
        return sendMessage("true", "", entity, DateUtil.DATE);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public String deleteById(String id) {
        try {
            Box box=boxService.findById(id);
            if(box!=null) {
                boxService.deleteById(id);
                Warehouse warehouse=warehouseService.findOne(box.getCkId());
                Double num=warehouse.getCount();
                warehouse.setCount(num-1);
                warehouseService.save(warehouse);
            }
            return sendSuccessMessage();
        }catch (Exception e){
            return sendFailMessage();
        }
    }

    @RequestMapping("/page")
    public String page(Box entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<Box> pageEntity = new PageEntity<>(start, size, page);
        boxService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(Box entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        if (StringUtils.isNotBlank(entity.getCkId())){
            parameterMap.put("ckId", entity.getCkId());
        }
        if (entity.getHgStatus()!=null){
            parameterMap.put("hgStatus", entity.getHgStatus());
        }
        return parameterMap;
    }
}

