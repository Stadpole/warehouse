package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.Account;
import com.bupt.entity.WarehouseOut;
import com.bupt.mapper.WarehouseOutMapper;
import com.bupt.repository.WarehouseOutRepository;
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
public class WarehouseOutService extends BasePageService<WarehouseOut, String> {
    @Autowired
    private WarehouseOutRepository warehouseOutRepository;
    @Autowired
    private WarehouseOutMapper warehouseOutMapper;

    public void save(WarehouseOut entity) {
        warehouseOutRepository.save(entity);
    }

    public WarehouseOut findById(String id) {
        return warehouseOutRepository.findOne(id);
    }
    public WarehouseOut findByHgId(String id) {
        return warehouseOutRepository.findByHgId(id);
    }
    public WarehouseOut findByCkIdAndHgIdAndSpId(String hgId) {
        return warehouseOutRepository.findByHgId(hgId);
    }
    public List<WarehouseOut> findAll() {
        return warehouseOutRepository.findAll();
    }
    public List<WarehouseOut> findAllByCkId(String id) {
        return warehouseOutRepository.findAllByCkId(id);
    }
    public void deleteById(String id) {
        warehouseOutRepository.delete(id);
    }
    public List<Account> findQuarter(){return warehouseOutMapper.findAccountQuarter();}
    public List<Account> findMonth(){return warehouseOutMapper.findAccountMonth();}
    public List<Account> findDay(Date start,Date end){return warehouseOutMapper.findAccountDay(start,end);}
    public List<WarehouseOut> findBySpIdAndCkId(String spId,String ckId){
        return warehouseOutRepository.findBySpIdAndCkId(spId,ckId);
    }
    public Account findSpRkSum(String ckId,String spId,Date start,Date end)
    {return warehouseOutMapper.findSpRkSum(ckId,spId,start,end);}
    public void pageByHql(PageEntity<WarehouseOut> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from WarehouseOut where 1=1 ");
       if (paramaMap.containsKey("outStatus")) {
            sql.append(" and outStatus =:outStatus ");
        }
         /*
        if (paramaMap.containsKey("power")) {
            sql.append(" and power =:power ");
        }*/
       super.pageByHql(sql.toString(), pageEntity, paramaMap);
        translate(pageEntity.getResults());
    }

    @Override
    protected void translate(List<WarehouseOut> list) {
        super.translate(list);
    }
}