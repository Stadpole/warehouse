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
import org.apache.commons.lang3.StringUtils;
import org.apache.naming.factory.SendMailFactory;
import org.hibernate.action.spi.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/warehouseOut")
public class WarehouseOutController extends BaseCommonController {
    @Autowired
    private WarehouseOutService warehouseOutService;
    @Autowired
    private WarehouseInService warehouseInService;
    @Autowired
    private BoxService boxService;
    @Autowired
    private WarehouseOutApplyService warehouseOutApplyService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RepertoryService repertoryService;
   //只有仓库管理员才有的权限
    @RequestMapping(value = "", method = RequestMethod.POST)
    @OperationLogger(moduleName = "仓库管理", option = "新增",operationDetail= "出库单")
    public String save(@RequestBody WarehouseOut entity) {
        try{
            if(entity!=null){
                //如果货柜物品全部出库，则将货柜状态变为可用0
                WarehouseIn warehouseIn=warehouseInService.findByHgId(entity.getHgId());
                if(warehouseIn!=null){
                    if(entity.getApplyCount()==warehouseIn.getRkCount()){
                        Box box=boxService.findByHgId(entity.getHgId());
                        box.setHgStatus(0);
                        boxService.save(box);
                    }
                 //出库申请单状态变为1  已同意
                    WarehouseOutApply warehouseOutApply=warehouseOutApplyService.findById(entity.getId());
                    if(warehouseOutApply!=null) {
                        warehouseOutApply.setApplyStatus(1);
                        warehouseOutApplyService.save(warehouseOutApply);
                    }
                entity.setId("");
                entity.setOutCount(entity.getApplyCount());
                entity.setOutStatus(1);
                entity.setOutStatus(0);
                warehouseOutService.save(entity);
               UserInfo userInfo=userInfoService.findById(entity.getYhId());
               if(userInfo!=null){
               final String email=userInfo.getEmail();
                try {
                 new MailSender()
                   .title("仓库秘钥")
                         .content("尊敬的用户" + "仓库"+"钥匙和"+"货柜钥匙分別为: " + sendNumber() + ",该验证码有效时间为2小時,请注意时间，不要外泄密码")
                         .contentType(MailContentTypeEnum.TEXT)
                        .targets(new ArrayList<String>() {{
                        add("2209495405@qq.com");
                         }})
                         .send();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                repertoryService.changeOut(entity.getCkId(),entity.getSpId(),entity);
                return sendSuccessMessage();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        warehouseOutService.save(entity);
        return sendSuccessMessage();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @OperationLogger(moduleName = "仓库管理", option = "更新",operationDetail= "出库单")
    public String update(@RequestBody WarehouseOut  entity) {
        if ((StringUtils.isNotBlank(entity.getId()))) {
            WarehouseOut warehouseOut = warehouseOutService.findById(entity.getId());
            BeanUtills.copyProperties(entity, warehouseOut);
            warehouseOutService.save(warehouseOut);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "出库单")
    public String findOne(@PathVariable(value = "id") String id) {
        WarehouseOut entity = warehouseOutService.findById(id);
        return sendMessage("true", "", entity, DateUtil.DATE);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @OperationLogger(moduleName = "仓库管理", option = "删除",operationDetail= "出库单")
    public String deleteById(String id) {
        warehouseOutService.deleteById(id);
        return sendSuccessMessage();
    }

    @RequestMapping("/page")
    @OperationLogger(moduleName = "仓库管理", option = "查询",operationDetail= "出库列表")
    public String page(WarehouseOut entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<WarehouseOut> pageEntity = new PageEntity<>(start, size, page);
        warehouseOutService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(WarehouseOut entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        if (entity.getOutStatus()!=null){
            parameterMap.put("outStatus", entity.getOutStatus());
        }
        return parameterMap;
    }
}

