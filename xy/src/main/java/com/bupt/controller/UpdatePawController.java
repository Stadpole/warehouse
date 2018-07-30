package com.bupt.controller;

import com.alibaba.fastjson.JSONObject;
import com.bupt.aop.OperationLogger;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.PasswordHelper;
import com.bupt.entity.UserInfo;
import com.bupt.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Stadpole on 2018/1/22.
 */

@RestController
@RequestMapping(value = "/password")
public class UpdatePawController {
    @Autowired
    private UserInfoService userInfoService;
    /**
     * 密码修改;
     *
     * @return
     */

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @OperationLogger(moduleName = "用户管理", option = "更新", operationDetail = "密码修改")
    @RequiresPermissions("common:get")//权限管理;
    public String updatePsw(String username,String email,String pwd,String newPwd) {
        JSONObject jsonObject = new JSONObject();
        PasswordHelper passwordHelper = new PasswordHelper();
        UserInfo userInfo = userInfoService.findByUsername(username);
        UserInfo entity=new UserInfo();
        entity.setPassword(pwd);
        entity.setNewPassword(newPwd);
        String md5Password = passwordHelper.encryptPassword(entity);
        String newPassword = passwordHelper.encryptPassword1(entity);
        if (userInfo != null) {
            if (md5Password.equals(userInfo.getPassword())) {
                entity.setPassword(newPassword);
                BeanUtills.copyProperties(entity, userInfo);
                userInfoService.save2(userInfo);
                jsonObject.put("msg", "密码修改成功");
            } else {
                jsonObject.put("msg", "密码输入错误");
            }
        } else {
            jsonObject.put("msg", "用户未登陆");
        }
        return jsonObject.toString();
    }

}
