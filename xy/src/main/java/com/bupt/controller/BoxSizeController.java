package com.bupt.controller;

import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.entity.BoxSize;
import com.bupt.service.BoxSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static java.sql.Types.NULL;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/boxSize")
public class BoxSizeController extends BaseCommonController {
    @Autowired
    private BoxSizeService boxSizeService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@RequestBody BoxSize entity) {
        boxSizeService.save(entity);
        return sendSuccessMessage();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody BoxSize entity) {
        if (entity.getHgSizeId()!= NULL) {
            BoxSize boxSize = boxSizeService.findById(entity.getHgSizeId());
            BeanUtills.copyProperties(entity, boxSize);
            boxSizeService.save(boxSize);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findOne(@PathVariable(value = "id") Integer id) {
        BoxSize entity = boxSizeService.findById(id);
        return sendMessage("true", "", entity, DateUtil.DATE);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable(value = "id") String id) {
        boxSizeService.deleteById(id);
        return sendSuccessMessage();
    }

    @RequestMapping("/page")
    public String page(BoxSize entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<BoxSize> pageEntity = new PageEntity<>(start, size, page);
        boxSizeService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(BoxSize entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        return parameterMap;
    }
}

