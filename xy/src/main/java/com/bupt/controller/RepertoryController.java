package com.bupt.controller;

import com.bupt.aop.OperationLogger;
import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.Repertory;
import com.bupt.entity.Warehouse;
import com.bupt.service.CommodityService;
import com.bupt.service.RepertoryService;
import com.bupt.service.WarehouseService;
import com.google.gson.Gson;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/repertories")
public class RepertoryController extends BaseCommonController {
    @Autowired
    private RepertoryService repertoryService;

    @RequestMapping("/page")
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "库存")
    public String page(Repertory entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<Repertory> pageEntity = new PageEntity<>(start, size, page);
        repertoryService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(Repertory entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        if (StringUtils.isNotBlank(entity.getCkId())){
            parameterMap.put("ckId", entity.getCkId());
        }
        if (StringUtils.isNotBlank(entity.getSpId())){
            parameterMap.put("spId", entity.getSpId());
        }
        if (StringUtils.isNotBlank(entity.getSpName())){
            parameterMap.put("spName", entity.getSpName());
        }
        return parameterMap;
    }

}

