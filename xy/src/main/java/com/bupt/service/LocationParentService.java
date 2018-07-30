package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.LocationParent;
import com.bupt.repository.LocationParentRepository;
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

public class LocationParentService extends BasePageService<LocationParent, String> {
    @Autowired
    private LocationParentRepository locationParentRepository;

    public void save(LocationParent entity) {
        locationParentRepository.save(entity);
    }

    public LocationParent findOne(Integer id) {
        LocationParent entity= locationParentRepository.findById(id);
        return entity;
    }
    public List<LocationParent> findAll(){
        return locationParentRepository.findAll();
    }


    public void deleteById(Integer id) {
        locationParentRepository.deleteById(id);
    }


    public void pageByHql(PageEntity<LocationParent> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from LocationParent where 1=1 ");
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
    protected void translate(List<LocationParent> lists) {
        super.translate(lists);
    }
}