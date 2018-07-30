package com.bupt.controller;

import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.entity.PlanLog;
import com.bupt.repository.PlanLogRepository;
import com.bupt.service.PlanLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/planLog")
public class PlanLogController extends BaseCommonController {
    @Autowired
    private PlanLogService planLogService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@RequestBody PlanLog entity) {
        planLogService.save(entity);
        return sendSuccessMessage();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody PlanLog entity) {
        if ((StringUtils.isNotBlank(entity.getId()))) {
            PlanLog planLog = planLogService.findById(entity.getId());
            BeanUtills.copyProperties(entity, planLog);
            planLogService.save(planLog);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable(value = "id") String id) {
        PlanLog entity = planLogService.findById(id);
        return sendMessage("true", "", entity, DateUtil.DATE);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable(value = "id") String id) {
        planLogService.deleteById(id);
        return sendSuccessMessage();
    }

    @RequestMapping("/page")
    public String page(PlanLog entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<PlanLog> pageEntity = new PageEntity<>(start, size, page);
        planLogService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(PlanLog entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        return parameterMap;
    }
}

