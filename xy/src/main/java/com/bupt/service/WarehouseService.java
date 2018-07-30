package com.bupt.service;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.bupt.aop.OperationLogger;
import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.*;
import com.bupt.repository.WarehouseRepository;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.*;

/**
 * Created by mengying on 2017/9/21.
 */
@Service
@Transactional

public class WarehouseService extends BasePageService<Warehouse, String> {
    @Autowired
    private WarehouseRepository warehouseRepository;

    public void save(Warehouse entity) {
        warehouseRepository.save(entity);
    }

    public Warehouse findOne(String id) {
        Warehouse entity= warehouseRepository.findOne(id);
        return entity;
    }
    public List<Warehouse> findByParentId(Integer parentId){
        return warehouseRepository.findByParentId(parentId);
    }
    public List<Warehouse> findAll(){
        return warehouseRepository.findAll();
    }


    public void deleteByCkId(String id) {
        warehouseRepository.deleteByCkId(id);
    }
    public Warehouse findByCkId(String ckId) {
      Warehouse entity=  warehouseRepository.findByCkId(ckId);
        return entity;
    }

    public Page<Warehouse> findByTime(Date str1, Date str2, Pageable pageable){
        return warehouseRepository.findByCreateTimeBetween(str1,str2,pageable);
    }
    public void pageByHql(PageEntity<Warehouse> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from Warehouse where 1=1 ");
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
    protected void translate(List<Warehouse> lists) {
        for (Warehouse warehouse: lists ) {
            if (warehouse.getLat() != null||warehouse.getLng()!=null){
                Double [] location={warehouse.getLng(),warehouse.getLat()};
                warehouse.setLocation(location);
            }

            }
        super.translate(lists);
    }
}