package com.bupt.common.utils;

import com.bupt.entity.UserInfo;
import com.bupt.service.UserInfoService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

/**
 * Created by Stadpole on 2017/12/26.
 */
public class PasswordHelper {
    @Autowired
    private UserInfoService userInfoService;
    public String encryptPassword(UserInfo userInfo) {
        String hashAlgorithmName = "MD5";
        int hashIterations = 2;
        String credentials=userInfo.getPassword();
        ByteSource credentialsSalt = ByteSource.Util.bytes(userInfo.getCredentialsSalt());
        Object password = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        return password.toString();
    }
    public String encryptPassword1(UserInfo userInfo) {
        String hashAlgorithmName = "MD5";
        int hashIterations = 2;
        String credentials=userInfo.getNewPassword();
        ByteSource credentialsSalt = ByteSource.Util.bytes(userInfo.getCredentialsSalt());
        Object password = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        return password.toString();
    }
    public String encryptPassword2(String username) {
        String hashAlgorithmName = "MD5";
        int hashIterations = 2;
        String credentials=username;
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //长度为几就循环几次
        for(int i=0; i<4; ++i){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
       String ss=sb.toString();
        ByteSource credentialsSalt = ByteSource.Util.bytes(ss);
        Object password = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        return password.toString();
    }
}
