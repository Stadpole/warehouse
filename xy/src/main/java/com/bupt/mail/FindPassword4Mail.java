package com.bupt.mail;

import com.alibaba.fastjson.JSONObject;
import com.bupt.common.utils.PasswordHelper;
import com.bupt.entity.Password;
import com.bupt.entity.UserInfo;
import com.bupt.mail.MailContentTypeEnum;
import com.bupt.mail.MailSender;
import com.bupt.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static com.bupt.common.utils.MessageUtil.sendFailMessage;
import static com.bupt.common.utils.MessageUtil.sendSuccessMessage;


/**
 * Created by Stadpole on 2018/3/9.
 */

@RestController
@RequestMapping(value = "/mail")
public class FindPassword4Mail {
    @Autowired
    private UserInfoService userInfoService;
    //ArrayList arrayList=new ArrayList();
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String find(@RequestBody Password entity) {
        String username=entity.getUsername();
        final String email=entity.getEmail();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        JSONObject jsonObject = new JSONObject();
        UserInfo userInfo=userInfoService.findByUsername(username);
        String regex = "[a-zA-Z0-9_]{6,12}+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}";
        boolean b = email.matches(regex);
        if(userInfo==null){
          jsonObject.put("msg", "用户不存在");
        }
        else if(email==null){
            jsonObject.put("msg", "邮箱不能为空");
        }
        else if(!b){
            jsonObject.put("msg", "请确认邮箱格式是否正确");
        }
        else {
            PasswordHelper passwordHelper=new PasswordHelper();
            String RanDom=passwordHelper.encryptPassword2(username);
            HttpSession session = request.getSession();
            session.setAttribute("RanDom",RanDom);
            try {
                new MailSender()
                        .title("找回密码")
                        .content("尊敬的用户" + username + ",仓库管理系统修改密码的验证码为" + RanDom + ",该验证码有效时间为30分钟，请注意自己的帐号安全，不要外泄密码")
                        .contentType(MailContentTypeEnum.TEXT)
                        .targets(new ArrayList<String>() {{
                            add(email);
                        }})
                        .send();
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonObject.put("msg", "邮件发送成功");
        }
        return jsonObject.toString();
    }
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public String changePassword(@RequestBody Password entity) {
        String username=entity.getUsername();
        String num1=entity.getOldPassword();
        String num2=entity.getNewPassword();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        HttpSession session=request.getSession();
       String number=session.getAttribute("RanDom").toString();
       UserInfo userInfo=userInfoService.findByUsername(username);
       if(number.equals(num1)){
           userInfo.setPassword(num2);
           userInfoService.save(userInfo);
           return sendSuccessMessage();
       }
       return sendFailMessage();
    }
}
