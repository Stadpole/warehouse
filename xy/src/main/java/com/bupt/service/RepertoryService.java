package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.Repertory;
import com.bupt.entity.WarehouseIn;
import com.bupt.entity.WarehouseOut;
import com.bupt.repository.RepertoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by mengying on 2017/9/21.
 */
@Service
@Transactional
public class RepertoryService extends BasePageService<Repertory, String> {
    @Autowired
    private RepertoryRepository repertoryRepository;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private CommodityService commodityService;

    public void save(Repertory entity) {
        repertoryRepository.save(entity);
    }
    public Repertory findByCkIDAndSpId(String ckId,String spId) {
        return repertoryRepository.findByCkIdAndSpId(ckId,spId);
    }
    public List<Repertory> findAll(){
        return repertoryRepository.findAll();
    }
    //入库时库存数量变化
    public void change(String ckId, String spId, WarehouseIn entity){
        Repertory repertory=findByCkIDAndSpId(ckId,spId);
        if(repertory!=null){
            repertory.setTotalCount(repertory.getTotalCount()+entity.getRkCount());
            save(repertory);
        }
        else{
            repertory=new Repertory();
            repertory.setTotalCount(0.0);
            repertory.setCkId(entity.getCkId());
            repertory.setSpId(entity.getSpId());
            repertory.setCkName(warehouseService.findByCkId(entity.getCkId()).getName());
           repertory.setSpName(commodityService.findBySpId(entity.getSpId()).getSpName());
           repertory.setSpBrand(commodityService.findBySpId(entity.getSpId()).getSpBrand());
            repertory.setTotalCount(repertory.getTotalCount()+entity.getRkCount());
            save(repertory);

        }
    }
    //出库时库存数量变化
    public void changeOut(String ckId, String spId, WarehouseOut entity){
        Repertory repertory=findByCkIDAndSpId(ckId,spId);
        if(repertory!=null){
            repertory.setTotalCount(repertory.getTotalCount()-entity.getOutCount());
            save(repertory);
        }
    }
    public void pageByHql(PageEntity<Repertory> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from Repertory where 1=1 ");
        if (paramaMap.containsKey("ckId")) {
            sql.append(" and ckId =:ckId ");
        }
        if (paramaMap.containsKey("spId")) {
            sql.append(" and spId =:spId ");
        }
        if (paramaMap.containsKey("spName")) {
            sql.append(" and spName =:spName ");
        }
        super.pageByHql(sql.toString(), pageEntity, paramaMap);
        translate(pageEntity.getResults());
    }

    @Override
    protected void translate(List<Repertory> lists) {}

}