package com.bupt.controller;

import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.entity.SysRole;
import com.bupt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController extends BaseCommonController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@RequestBody SysRole entity) {
        roleService.save(entity);
        return sendSuccessMessage();
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody SysRole entity) {
        if (entity.getId()!=null) {
            SysRole role = roleService.findById(entity.getId());
            BeanUtills.copyProperties(entity, role);
            roleService.save(role);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String findOne(Integer id) {
        SysRole entity = roleService.findById(id);
        return sendMessage("true", "", entity, DateUtil.DATE);
    }

    @RequestMapping(value = "",method = RequestMethod.DELETE)
    public String deleteById(Integer id) {
        roleService.deleteById(id);
        return sendSuccessMessage();
    }

    @RequestMapping("/page")
    public String page(SysRole entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<SysRole> pageEntity = new PageEntity<>(start, size, page);
        roleService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(SysRole entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        return parameterMap;
    }
}

