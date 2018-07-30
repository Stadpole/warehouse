package com.bupt.controller;

import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.common.utils.NumberUtills;
import com.bupt.entity.Commodity;
import com.bupt.service.CommodityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/commodity")
public class CommodityController extends BaseCommonController {
    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@RequestBody Commodity entity) {
        entity.setSpId(NumberUtills.getNumber());
        commodityService.save(entity);
        return sendSuccessMessage();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody Commodity entity) {
        if ((StringUtils.isNotBlank(entity.getSpId()))) {
            Commodity commodity = commodityService.findById(entity.getSpId());
            BeanUtills.copyProperties(entity, commodity);
            commodityService.save(commodity);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable(value = "id") String id) {
        Commodity entity = commodityService.findById(id);
        return sendMessage("true", "", entity, DateUtil.DATE);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public String deleteById(String id) {
        commodityService.deleteById(id);
        return sendSuccessMessage();
    }

    @RequestMapping("/page")
    public String page(Commodity entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<Commodity> pageEntity = new PageEntity<>(start, size, page);
        commodityService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(Commodity entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        return parameterMap;
    }
}

