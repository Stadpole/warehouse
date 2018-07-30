package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;

import com.bupt.entity.Account;
import com.bupt.entity.WarehouseIn;
import com.bupt.mapper.WarehouseInMapper;
import com.bupt.repository.WarehouseInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mengying on 2017/9/21.
 */
@Service
@Transactional
public class WarehouseInService extends BasePageService<WarehouseIn, String> {
    @Autowired
    private WarehouseInRepository warehouseInRepository;
    @Autowired
    private WarehouseInMapper warehouseInMapper;


    public void save(WarehouseIn entity) {
        warehouseInRepository.save(entity);
    }

    public WarehouseIn findById(String id) {
        return warehouseInRepository.findOne(id);
    }
    public  WarehouseIn findByHgId(String id) {
        return warehouseInRepository.findByHgId(id);
    }
    public  WarehouseIn findByCkId(String id) {
        return warehouseInRepository.findByCkId(id);
    }
    public  List<WarehouseIn> findBySpIdAndCkId(String spId,String ckId) {
        return warehouseInRepository.findBySpIdAndCkId(spId,ckId);
    }
    public  List<WarehouseIn> findAllByCkId(String ckId) {
        return warehouseInRepository.findAllByCkId(ckId);
    }
    public  List<WarehouseIn> findAll() {
        return warehouseInRepository.findAll();
    }
    public void deleteById(String id) {
        warehouseInRepository.delete(id);
    }
    public List<Account> findQuarter(){return warehouseInMapper.findAccountQuarter();}
    public Account findSpRkSum(String ckId,String spId,Date start,Date end){
        return warehouseInMapper.findSpRkSum(ckId,spId,start,end);}
    public List<Account> findMonth(){return warehouseInMapper.findAccountMonth();}
    public List<Account>  findDay(Date start, Date end){return warehouseInMapper.findAccountDay(start,end);}
    public void pageByHql(PageEntity<WarehouseIn> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from WarehouseIn where 1=1 ");
        if (paramaMap.containsKey("ckId")) {
            sql.append(" and ckId =:ckId ");
        }
        if (paramaMap.containsKey("hgId")) {
            sql.append(" and hgId =:hgId ");
        }
       super.pageByHql(sql.toString(), pageEntity, paramaMap);
        translate(pageEntity.getResults());
    }

    @Override
    protected void translate(List<WarehouseIn> list) {
        super.translate(list);
    }
}