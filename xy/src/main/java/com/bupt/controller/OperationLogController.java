package com.bupt.controller;

import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.entity.OperationLog;
import com.bupt.service.OperationLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/operationLog")
public class OperationLogController extends BaseCommonController {
    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@RequestBody OperationLog entity) {
        operationLogService.save(entity);
        return sendSuccessMessage();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody OperationLog entity) {
        if ((StringUtils.isNotBlank(entity.getId()))) {
            OperationLog operationLog = operationLogService.findById(entity.getId());
            BeanUtills.copyProperties(entity, operationLog);
            operationLogService.save(operationLog);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable(value = "id") String id) {
        OperationLog entity = operationLogService.findById(id);
        return sendMessage("true", "", entity, DateUtil.DATE);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable(value = "id") String id) {
        operationLogService.deleteById(id);
        return sendSuccessMessage();
    }

    @RequestMapping("/page")
    public String page(OperationLog entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<OperationLog> pageEntity = new PageEntity<>(start, size, page);
        operationLogService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(OperationLog entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        return parameterMap;
    }
}

