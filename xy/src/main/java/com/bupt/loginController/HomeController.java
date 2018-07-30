package com.bupt.loginController;


/**
 * Created by Stadpole on 2017/12/19.
 */
import com.alibaba.fastjson.JSONObject;
import com.bupt.common.base.BaseCommonController;
import com.bupt.entity.UserInfo;
import com.bupt.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HomeController extends BaseCommonController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String ajaxLogin(@RequestBody UserInfo userInfo) {
         {
        System.out.println("进入controller");
       /* boolean rememberMe=false;
        if(StringUtils.isNotEmpty(userInfo.getRemember())){
            if(userInfo.getRemember()=="1"){  rememberMe=true;}
            else
                rememberMe=false;
        }*/

       JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());
        try {        Session session = subject.getSession();

            System.out.println("try");
            subject.login(token);
            session.setTimeout(86400000);        //一天
           // session.setTimeout(1000);        //一天
            UserInfo entity=userInfoService.findByUsername(userInfo.getUsername());
            session.setAttribute("userId",entity.getUid());
            session.setAttribute("user",userInfo.getUsername());
            jsonObject.put("token", session.getId());
            jsonObject.put("msg", "登录成功");
            jsonObject.put("username",userInfo.getUsername());
            jsonObject.put("yhId",entity.getUid());
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
         }
    }
}