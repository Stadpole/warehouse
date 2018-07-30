package com.bupt.mail;

/**
 * Created by Stadpole on 2018/1/22.
 */
import java.util.ArrayList;

public class TestMail {
    public static void main(String[] args) throws Exception
    {
        /*new MailSender()
                .title("测试SpringBoot发送邮件")
                .content("简单文本内容发送")
                .contentType(MailContentTypeEnum.TEXT)
                .targets(new ArrayList<String>(){{
                    add("2209495405@qq.com");
                }})
                .send();*/
       String RanDom="aaaa";
       String  Name="aaaa";
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