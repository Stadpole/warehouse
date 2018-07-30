package com.bupt.mail;

import com.bupt.entity.UserInfo;
import com.bupt.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Stadpole on 2018/1/22.
 */
@Service
public class FindPasswordService {
    @Autowired
    private UserInfoService userInfoService;
    public boolean isTrue(String username,String email){
        UserInfo userInfo=userInfoService.findByUsername(username);
        if(userInfo!=null){
            String userEmail=userInfo.getEmail();
            if(userEmail.equals(email)){
                return true;
            }
        }
        return false;
    }

    public void SendEmail(String RanDom, String Name)throws Exception
    {
        new MailSender()
                .title("找回密码")
                .content("尊敬的用户"+Name+",仓库管理系统修改密码的验证码为"+RanDom+",该验证码有效时间为30分钟，请注意自己的帐号安全，不要外泄密码")
                .contentType(MailContentTypeEnum.TEXT)
                .targets(new ArrayList<String>(){{
                    add("2209495405@qq.com");
                }})
                .send();

    }
}
