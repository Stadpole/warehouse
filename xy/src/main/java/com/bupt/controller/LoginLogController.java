package com.bupt.controller;

import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.entity.LoginLog;
import com.bupt.service.LoginLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/loginLog")
public class LoginLogController extends BaseCommonController {
    @Autowired
    private LoginLogService loginLogService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@RequestBody LoginLog entity) {
        loginLogService.save(entity);
        return sendSuccessMessage();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody LoginLog entity) {
        if ((StringUtils.isNotBlank(entity.getId()))) {
            LoginLog loginLog = loginLogService.findById(entity.getId());
            BeanUtills.copyProperties(entity, loginLog);
            loginLogService.save(loginLog);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable(value = "id") String id) {
        LoginLog entity = loginLogService.findById(id);
        return sendMessage("true", "", entity, DateUtil.DATE_PATTERN_3);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable(value = "id") String id) {
        loginLogService.deleteById(id);
        return sendSuccessMessage();
    }

    @RequestMapping("/page")
    public String page(LoginLog entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<LoginLog> pageEntity = new PageEntity<>(start, size, page);
        loginLogService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(LoginLog entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        return parameterMap;
    }
}

