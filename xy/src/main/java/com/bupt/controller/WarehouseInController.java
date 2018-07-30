package com.bupt.controller;

import com.bupt.aop.OperationLogger;
import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.DateUtil;
import com.bupt.entity.*;
import com.bupt.mail.MailContentTypeEnum;
import com.bupt.mail.MailSender;
import com.bupt.service.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/warehouseIn")
public class WarehouseInController extends BaseCommonController {
    @Autowired
    private WarehouseInService warehouseInService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private BoxService boxService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private WarehouseApplyService warehouseApplyService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RepertoryService repertoryService;
    @Autowired
    private WarehouseOutService warehouseOutService;
    //只有倉庫管理員才有的權限
    @RequiresPermissions("warehouse:add")//权限管理;
    @RequestMapping(value = "", method = RequestMethod.POST)
    @OperationLogger(moduleName = "仓库管理", option = "审核",operationDetail= "入库")
    public String save(@RequestBody WarehouseIn entity) {
        try{
            if(entity!=null){
                //货柜状态变为1  不可用
                if(StringUtils.isNotBlank(entity.getHgId())){
                    Box box=boxService.findByHgId(entity.getHgId());
                    box.setHgStatus(1);
                    boxService.save(box);
                }
                //入库申请单状态变为1  已同意
                WarehouseApply warehouseApply=warehouseApplyService.findById(entity.getId());
                if(warehouseApply!=null) {
                    warehouseApply.setApplyStatus(1);
                    warehouseApplyService.save(warehouseApply);
                }
                entity.setId("");
               entity.setRkCount(entity.getApplyCount());
               entity.setRkStatus(1);
                warehouseInService.save(entity);
                UserInfo userInfo=userInfoService.findById(entity.getYhId());
                if(userInfo!=null){
                   final String email=userInfo.getEmail();
                    try {
                        new MailSender()
                                .title("倉庫秘鑰")
                                .content("尊敬的用户" + "倉庫"+"鑰匙和"+"貨櫃鑰匙分別為: " + sendNumber() + ",该验证码有效时间为2小時,請注意時間，不要外泄密码")
                                .contentType(MailContentTypeEnum.TEXT)
                                .targets(new ArrayList<String>() {{
                                    add(email);
                                }})
                                .send();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                repertoryService.change(entity.getCkId(),entity.getSpId(),entity);
                return sendSuccessMessage();
            }
            else{
                return sendFailMessage();
            }
            //库存数量变化

        }catch (Exception e){
            return sendFailMessage();
        }
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @RequiresPermissions("warehouse:add")//权限管理;
    @OperationLogger(moduleName = "仓库管理", option = "更新",operationDetail= "入库单")
    public String update(@RequestBody WarehouseIn entity) {
        if ((StringUtils.isNotBlank(entity.getId()))) {
            WarehouseIn warehouseIn = warehouseInService.findById(entity.getId());
            BeanUtills.copyProperties(entity, warehouseIn);
            warehouseInService.save(warehouseIn);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }
/**
 * 传入货柜ID
 * */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @RequiresPermissions("common:get")//权限管理;
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "入库单")
    public String findOne(String hgId) {
        WarehouseIn entity = warehouseInService.findByHgId(hgId);
        WarehouseOut warehouseOut=warehouseOutService.findByCkIdAndHgIdAndSpId(hgId);
        double num=0;
        if(warehouseOut!=null){
            num=warehouseOut.getOutCount();
        }
        Double count=entity.getRkCount()-num;
        try {
            if (entity != null&&count!=0) {
                entity.setCommodity(commodityService.findById(entity.getSpId()));
                entity.setRkCount(count);
                return sendMessage("true", "", entity, DateUtil.DATE);
            }
            else {
                return "Noting in the box!";
            }
        }catch (Exception e){
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @OperationLogger(moduleName = "仓库管理", option = "删除",operationDetail= "入库单")
    @RequiresPermissions("warehouse:manage")//权限管理;
    public String deleteById(String id) {
        warehouseInService.deleteById(id);
        return sendSuccessMessage();
    }

    @RequestMapping("/page")
    @RequiresPermissions("common:get")//权限管理;
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "入库列表")
    public String page(WarehouseIn entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<WarehouseIn> pageEntity = new PageEntity<>(start, size, page);
        warehouseInService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(WarehouseIn entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        if (StringUtils.isNotBlank(entity.getCkId())){
            parameterMap.put("ckId", entity.getCkId());
        }
        if (StringUtils.isNotBlank(entity.getHgId())){
            parameterMap.put("hgId", entity.getHgId());
        }
        return parameterMap;
    }
}

