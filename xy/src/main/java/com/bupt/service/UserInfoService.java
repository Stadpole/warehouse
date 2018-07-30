package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.PasswordHelper;
import com.bupt.entity.UserInfo;
import com.bupt.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mengying on 2017/9/21.
 */
@Service
@Transactional
public class UserInfoService extends BasePageService<UserInfo, String> {
    @Autowired
    private UserInfoRepository userInfoRepository;

    public void save(UserInfo entity) {
        PasswordHelper passwordHelper=new PasswordHelper();
        entity.setPassword(passwordHelper.encryptPassword(entity));
        userInfoRepository.save(entity);
    }
    public void save2(UserInfo entity) {

        userInfoRepository.save(entity);
    }
    public List<UserInfo> findAll(){return userInfoRepository.findAll();}
    public UserInfo findById(Integer id){return userInfoRepository.findByUid(id) ;}
    public UserInfo findByUsername(String name) {
        return userInfoRepository.findByUsername(name);
    }

    public void deleteById(Integer id) {
        userInfoRepository.deleteByUid(id);
    }

    public void pageByHql(PageEntity<UserInfo> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from UserInfo where 1=1 ");
        if (paramaMap.containsKey("gender")) {
            sql.append(" and gender =:gender ");
        }
        if (paramaMap.containsKey("job")) {
            sql.append(" and job =:job ");
        }
        if (paramaMap.containsKey("state")) {
            sql.append(" and state =:state ");
        }
        if (paramaMap.containsKey("username")) {
            sql.append(" and username =:username ");
        }
        if (paramaMap.containsKey("name")) {
            sql.append(" and name =:name ");
        }
        if (paramaMap.containsKey("phone")) {
            sql.append(" and phone =:phone ");
        }
        if (paramaMap.containsKey("email")) {
           // sql.append(" and email like:email");

           sql.append(" and email =:email ");
        }
       super.pageByHql(sql.toString(), pageEntity, paramaMap);
        translate(pageEntity.getResults());
    }

    @Override
    protected void translate(List<UserInfo> list) {
        super.translate(list);
    }
}