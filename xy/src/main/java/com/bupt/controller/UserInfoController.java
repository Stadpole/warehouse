package com.bupt.controller;

import com.alibaba.fastjson.JSONObject;
import com.bupt.aop.OperationLogger;
import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.PasswordHelper;
import com.bupt.entity.Password;
import com.bupt.entity.UserInfo;
import com.bupt.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController extends BaseCommonController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户保存;
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @OperationLogger(moduleName = "用户管理", option = "新增", operationDetail = "用户信息")
    @RequiresPermissions("userInfo:add")//权限管理;
    public String save(@RequestBody UserInfo entity) {
        userInfoService.save(entity);
        return sendSuccessMessage();
    }

    /**
     * 用户更新;
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @OperationLogger(moduleName = "用户管理", option = "更新", operationDetail = "用户信息")
    @RequiresPermissions("userInfo:update")//权限管理;
    public String update(@RequestBody UserInfo entity) {
        if (entity.getUid() != null) {
            UserInfo userInfo = userInfoService.findById(entity.getUid());
            BeanUtills.copyProperties(entity, userInfo);
            userInfoService.save(userInfo);
            return sendSuccessMessage();
        } else {
            return sendFailMessage();
        }
    }

    /**
     * 单一用户查询;
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @OperationLogger(moduleName = "用户管理", option = "查询", operationDetail = "个人信息")
    @RequiresPermissions("common:get")//权限管理;
    public UserInfo findOne(String username) {
        UserInfo entity = userInfoService.findByUsername(username);
        return entity;
    }

    /**
     * 用户删除;
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @OperationLogger(moduleName = "用户管理", option = "删除", operationDetail = "用户信息")
    @RequiresPermissions("userInfo:Del")//权限管理;
    public String deleteById(Integer uid) {
        userInfoService.deleteById(uid);
        return sendSuccessMessage();
    }
    /**
     * 用户退出;
     *
     * @return
     */
    @PostMapping(value = "/logout")
    @RequiresPermissions("userInfo:logout")//权限管理;
    public String logout(@RequestBody UserInfo userInfo) {
        JSONObject jsonObject = new JSONObject();
        SecurityUtils.getSubject().logout();
        userInfo.setState(0);
        jsonObject.put("msg", "退出成功");
        return jsonObject.toString();
    }
    /**
     * 用户修改密码;
     *
     * @return
     */
    @RequestMapping(value = "/newPassword", method = RequestMethod.PUT)
    @OperationLogger(moduleName = "用户管理", option = "更新", operationDetail = "密码修改")
    public String newPassword(@RequestBody Password entity) {
        Integer id=entity.getId();
        String num1=entity.getOldPassword();
        String num2=entity.getNewPassword();
        JSONObject jsonObject = new JSONObject();
        PasswordHelper passwordHelper=new PasswordHelper();
        UserInfo userInfo=userInfoService.findById(id);
        String password=userInfo.getPassword();
        userInfo.setPassword(num1);
        String number1=passwordHelper.encryptPassword(userInfo);
        if(number1.equals(password)){
            userInfo.setPassword(num2);
          userInfoService.save(userInfo);
            jsonObject.put("msg", "修改成功");
        }else{
            jsonObject.put("msg", "修改失败");
        }
        return jsonObject.toString();
    }

    /**
     * 显示所有用户;
     *
     * @return
     */
    @RequestMapping("/page")
    @RequiresPermissions("userInfo:view")//权限管理;
    @OperationLogger(moduleName = "用户管理", option = "查询", operationDetail = "用户列表")
    public String page(UserInfo entity, int page, int size) {
        int start = (page - 1) * size;
        PageEntity<UserInfo> pageEntity = new PageEntity<>(start, size, page);
        userInfoService.pageByHql(pageEntity, buildParameter(entity));
        return sendSuccessMessage(pageEntity);
    }

    private Map<String, Object> buildParameter(UserInfo entity) {
        Map<String, Object> parameterMap = new HashMap<>();
        if (entity.getGender()!=null){
            parameterMap.put("gender", entity.getGender());
        }
        if (StringUtils.isNotBlank(entity.getJob())){
            parameterMap.put("job", entity.getJob());
        }
        if (entity.getState()!=null){
            parameterMap.put("state", entity.getState());
        }
        if (StringUtils.isNotBlank(entity.getName())){
            parameterMap.put("name", entity.getName());
        }
        if (StringUtils.isNotBlank(entity.getUsername())){
            parameterMap.put("username", entity.getUsername());
        }
        if (StringUtils.isNotBlank(entity.getPhone())){
            parameterMap.put("phone", entity.getPhone());
        }
        if (StringUtils.isNotBlank(entity.getEmail())){
            parameterMap.put("email", entity.getEmail());
        }
        return parameterMap;
    }
}

