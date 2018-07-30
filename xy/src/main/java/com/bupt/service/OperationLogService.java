package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.OperationLog;
import com.bupt.repository.OperationLogRepository;
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
public class OperationLogService extends BasePageService<OperationLog, String> {
    @Autowired
    private OperationLogRepository operationLogRepository;

    public void save(OperationLog entity) {
        operationLogRepository.save(entity);
    }

    public OperationLog findById(String id) {
        return operationLogRepository.findOne(id);
    }

    public void deleteById(String id) {
        operationLogRepository.delete(id);
    }

    public void pageByHql(PageEntity<OperationLog> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from OperationLog where 1=1 ");
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
    protected void translate(List<OperationLog> list) {
        super.translate(list);
    }
}