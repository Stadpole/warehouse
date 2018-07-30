package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.LoginLog;
import com.bupt.repository.LoginLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by mengying on 2017/9/21.
 */
@Service
@Transactional
public class LoginLogService extends BasePageService<LoginLog, String> {
    @Autowired
    private LoginLogRepository loginLogRepository;

    public void save(LoginLog entity) {
        loginLogRepository.save(entity);
    }

    public LoginLog findById(String id) {
        return loginLogRepository.findOne(id);
    }

    public void deleteById(String id) {
        loginLogRepository.delete(id);
    }

    public void pageByHql(PageEntity<LoginLog> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from LoginLog where 1=1 ");
       /* if (paramaMap.containsKey("name")) {
            sql.append(" and name =:name ");
        }
        if (paramaMap.containsKey("power")) {
            sql.append(" and power =:power ");
        }*/
       super.pageByHql(sql.toString(), pageEntity, paramaMap);
        translate(pageEntity.getResults());
    }

    @Override
    protected void translate(List<LoginLog> list) {
        super.translate(list);
    }
}