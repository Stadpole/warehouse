package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.Repertory;
import com.bupt.entity.WarehouseParent;
import com.bupt.repository.WarehouseParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mengying on 2017/9/21.
 */
@Service
@Transactional

public class WarehouseParentService extends BasePageService<WarehouseParent, String> {
    @Autowired
    private WarehouseParentRepository warehouseParentRepository;

    public void save(WarehouseParent entity) {
        warehouseParentRepository.save(entity);
    }

    public WarehouseParent findOne(Integer id) {
        WarehouseParent entity= warehouseParentRepository.findById(id);
        return entity;
    }
    public WarehouseParent findByLocationName(String locationName) {

        return warehouseParentRepository.findByName(locationName);
    }
    public List<WarehouseParent> findAll(){
        return warehouseParentRepository.findAll();
    }
    public List<WarehouseParent> findByParentId(Integer id){
        return warehouseParentRepository.findByParentId(id);
    }

    public void deleteById(Integer id) {
        warehouseParentRepository.deleteById(id);
    }


    public void pageByHql(PageEntity<WarehouseParent> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from WarehouseParent where 1=1 ");
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
    protected void translate(List<WarehouseParent> lists) {
        super.translate(lists);
    }
}