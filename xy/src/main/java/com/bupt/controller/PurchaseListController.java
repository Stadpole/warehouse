package com.bupt.controller;

import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.common.utils.NumberUtills;
import com.bupt.entity.Box;
import com.bupt.entity.PurchaseList;
import com.bupt.service.BoxService;
import com.bupt.service.PurchaseListService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/purchaseList")
public class PurchaseListController extends BaseCommonController {
    @Autowired
    private PurchaseListService purchaseListService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@RequestBody PurchaseList entity) {
        purchaseListService.save(entity);
        return sendSuccessMessage();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody PurchaseList entity) {
        if ((StringUtils.isNotBlank(entity.getId()))) {
            PurchaseList purchaseList = purchaseListService.findById(entity.getId());
            BeanUtills.copyProperties(entity, purchaseList);
            purchaseListService.save(purchaseList);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable(value = "id") String id) {
        PurchaseList entity = purchaseListService.findById(id);
        return sendMessage("true", "", entity, DateUtil.DATE);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable(value = "id") String id) {
        purchaseListService.deleteById(id);
        return sendSuccessMessage();
    }

    @RequestMapping("/page")
    public String page(PurchaseList entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<PurchaseList> pageEntity = new PageEntity<>(start, size, page);
        purchaseListService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(PurchaseList entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        return parameterMap;
    }
}

