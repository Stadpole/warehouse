package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.PlanLog;
import com.bupt.repository.PlanLogRepository;
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
public class PlanLogService extends BasePageService<PlanLog, String> {
    @Autowired
    private PlanLogRepository planLogRepository;

    public void save(PlanLog entity) {
        planLogRepository.save(entity);
    }

    public PlanLog findById(String id) {
        return planLogRepository.findOne(id);
    }

    public void deleteById(String id) {
        planLogRepository.delete(id);
    }

    public void pageByHql(PageEntity<PlanLog> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from PlanLog where 1=1 ");
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
    protected void translate(List<PlanLog> list) {
        super.translate(list);
    }
}