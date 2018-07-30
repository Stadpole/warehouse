package com.bupt.controller;

import com.bupt.aop.OperationLogger;
import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.entity.SysPermission;
import com.bupt.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/permission")
public class PermissionController extends BaseCommonController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@RequestBody SysPermission entity) {
        permissionService.save(entity);
        return sendSuccessMessage();
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody SysPermission entity) {
        if (entity.getId()!=null) {
            SysPermission permission = permissionService.findById(entity.getId());
            BeanUtills.copyProperties(entity, permission);
            permissionService.save(permission);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable(value = "id") Integer id) {
        SysPermission entity = permissionService.findById(id);
        return sendMessage("true", "", entity, DateUtil.DATE);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable(value = "id") Integer id) {
        permissionService.deleteById(id);
        return sendSuccessMessage();
    }

    @RequestMapping("/page")
    @OperationLogger(moduleName = "权限管理", option = "查看", operationDetail = "权限列表")
    public String page(SysPermission entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<SysPermission> pageEntity = new PageEntity<>(start, size, page);
        permissionService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(SysPermission entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        return parameterMap;
    }
}

