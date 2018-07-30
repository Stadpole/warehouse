package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.DateUtil;
import com.bupt.common.utils.MapSort;
import com.bupt.entity.*;
import com.bupt.repository.WarehouseOutApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by mengying on 2017/9/21.
 */
@Service
@Transactional
public class WarehouseOutApplyService extends BasePageService<WarehouseOutApply, String> {
    @Autowired
    private WarehouseOutApplyRepository warehouseOutApplyRepository;
    @Autowired
    private BoxService boxService;
    @Autowired
    private WarehouseInService warehouseInService;
    @Autowired
    private WarehouseOutService warehouseOutService;
    @Autowired
    private WarehouseService warehouseService;
    public void save(WarehouseOutApply entity) {
        warehouseOutApplyRepository.save(entity);
    }

    public WarehouseOutApply findById(String id) {
        return warehouseOutApplyRepository.findOne(id);
    }
    public  WarehouseOutApply findByHgId(String id) {
        return warehouseOutApplyRepository.findByHgId(id);
    }
    public  List<WarehouseOutApply> findAllByCkId(String ckId) {
        return warehouseOutApplyRepository.findAllByCkId(ckId);
    }

    public void deleteById(String id) {
        warehouseOutApplyRepository.delete(id);
    }
    public Map<String,Double> schedulingHg(String ckId,String spId,Double count){
        Map<String,Double> mapTemp=new HashMap<>();
        Map<String,Double> InMap=new HashMap<>();
        Set<String> setIn=InMap.keySet();
        Map<String,Double> OutMap=new HashMap<>();
        Set<String> setOut=OutMap.keySet();
        List<WarehouseIn> warehouseInList = warehouseInService.findBySpIdAndCkId(spId,ckId);
        List<WarehouseOut> warehouseOutList=warehouseOutService.findBySpIdAndCkId(spId,ckId);
       for(WarehouseIn warehouseIn:warehouseInList){
           if(setIn.contains(warehouseIn.getHgId())){
               double temp=InMap.get(warehouseIn.getHgId())+ warehouseIn.getRkCount();
               InMap.put(warehouseIn.getHgId(),temp);
           }
           else{
               double temp=warehouseIn.getRkCount();
               InMap.put(warehouseIn.getHgId(),temp);
           }
       }
        for(WarehouseOut warehouseOut:warehouseOutList){
            if(setOut.contains(warehouseOut.getHgId())){
                double temp=OutMap.get(warehouseOut.getHgId())+ warehouseOut.getOutCount();
                OutMap.put(warehouseOut.getHgId(),temp);
            }
            else{
                double temp=warehouseOut.getOutCount();
                OutMap.put(warehouseOut.getHgId(),temp);
            }
        }
        //获得每个仓库实际库存量=入库-出库
        Iterator<String> iterator=setIn.iterator();
         while(iterator.hasNext()){
             String ss=iterator.next();
             double result=InMap.get(ss);
             for (String str : setOut) {
                if(ss.equals(str)){
                    double temp=OutMap.get(str);
                    result=result-temp;
                    break;
                }
             }
             mapTemp.put(ss,result);
         }
     Iterator<String> iterator1=mapTemp.keySet().iterator();
         Map<String,Double> map=new HashMap<>();
        while(iterator1.hasNext()&&count>0){
            String temp=iterator1.next();
            if(count>=mapTemp.get(temp)){
                count=count-mapTemp.get(temp);
                map.put(temp,mapTemp.get(temp));
            }
            else if(count<mapTemp.get(temp)){
                map.put(temp,count);
            }
        }
        return map;
    }
    public void pageByHql(PageEntity<WarehouseOutApply> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from WarehouseOutApply where 1=1 ");
        if (paramaMap.containsKey("yhId")) {
            sql.append(" and yhId =:yhId ");
        }
        if (paramaMap.containsKey("ckManager")) {
            sql.append(" and ckManager =:ckManager ");
        }
        if (paramaMap.containsKey("applyStatus")) {
            sql.append(" and applyStatus =:applyStatus ");
        }
        if (paramaMap.containsKey("outStatus")) {
            sql.append(" and outStatus =:outStatus ");
        }
       super.pageByHql(sql.toString(), pageEntity, paramaMap);
        translate(pageEntity.getResults());
    }

    @Override
    protected void translate(List<WarehouseOutApply> list) {
        super.translate(list);
    }
}