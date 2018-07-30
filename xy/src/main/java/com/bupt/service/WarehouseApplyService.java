package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.Box;
import com.bupt.entity.WarehouseApply;

import com.bupt.repository.WarehouseApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by mengying on 2017/9/21.
 */
@Service
@Transactional
public class WarehouseApplyService extends BasePageService<WarehouseApply, String> {
    @Autowired
    private WarehouseApplyRepository warehouseApplyRepository;
    @Autowired
    private BoxService boxService;

    public void save(WarehouseApply entity) {
        warehouseApplyRepository.save(entity);
    }

    public WarehouseApply findById(String id) {
        return warehouseApplyRepository.findOne(id);
    }
    public  WarehouseApply findByHgId(String id) {
        return warehouseApplyRepository.findByHgId(id);
    }
    public  List<WarehouseApply> findAllByCkId(String ckId) {
        return warehouseApplyRepository.findAllByCkId(ckId);
    }
    public void deleteById(String id) {
        warehouseApplyRepository.delete(id);
    }
    public Map<String,Double> schedulingHg(String ckId,Double count){
        List<Box> boxList=boxService.findByCkIdAndHgStatus(ckId,1);
        Map<String,Double> map=new HashMap<>();
        Collections.sort(boxList,new Comparator<Box>() {
            @Override
            public int compare(Box o1, Box o2) {
                // TODO Auto-generated method stub
                return o1.getHgSizeId()-o2.getHgSizeId();
            }
        });
        int num0=0,num1=0,num2=0;
        for(Box box:boxList){
            if(box.getHgSizeId()==0){
                num0++;
            }
            if(box.getHgSizeId()==1){
                num1++;
            }
            if(box.getHgSizeId()==2){
                num2++;
            }
        }
        //先放小柜子
        int countSize0= (int) Math.ceil(count/5);
        int countSize1= (int) Math.ceil((count-num0*5.0)/10);
        int countSize2= (int) Math.ceil((count-num0*5.0-num1*10)/20);
        if(countSize0<=num0){
            int tempCount=1;
            for(Box box:boxList){
                if(box.getHgSizeId()==0){
                    if(tempCount==countSize0){
                        Double result=count-5*(tempCount-1);
                        box.setHgStatus(1);
                        map.put(box.getHgId(), result);
                        boxService.save(box);
                        break;
                    }
                    else {
                        box.setHgStatus(1);
                        map.put(box.getHgId(), 5.0);
                        boxService.save(box);
                        tempCount++;
                    }
                }
            }
        }
        else if(countSize1>=0&&countSize1<=num1) {
            for (Box box : boxList) {
                if (box.getHgSizeId() == 0) {
                    box.setHgStatus(1);
                    map.put(box.getHgId(), 5.0);
                    boxService.save(box);
                }
            }
            int tempCount=1;
            for (Box box : boxList) {
                if (box.getHgSizeId() == 1) {
                    if (tempCount == countSize1) {
                        Double result = count - 5 *num0-10*(tempCount-1);
                        box.setHgStatus(1);
                        map.put(box.getHgId(), result);
                        boxService.save(box);
                        break;
                    } else {
                        box.setHgStatus(1);
                        map.put(box.getHgId(), 10.0);
                        boxService.save(box);
                        tempCount++;
                    }
                }
            }
        }
        else{
            for (Box box : boxList) {
                if (box.getHgSizeId() == 0) {
                    box.setHgStatus(1);
                    map.put(box.getHgId(), 5.0);
                    boxService.save(box);
                }
                if (box.getHgSizeId() == 1) {
                    box.setHgStatus(1);
                    map.put(box.getHgId(),10.0);
                    boxService.save(box);
                }
            }
            int tempCount=1;
            for (Box box : boxList) {
                if (box.getHgSizeId() == 2) {
                    if (tempCount == countSize2) {
                        Double result = count - 5 *num0-10*num2-20*(tempCount-1);
                        box.setHgStatus(1);
                        map.put(box.getHgId(), result);
                        boxService.save(box);
                        break;
                    } else {
                        box.setHgStatus(1);
                        map.put(box.getHgId(), 20.0);
                        boxService.save(box);
                        tempCount++;
                    }
                }
            }

        }

        return map;
    }
    public void pageByHql(PageEntity<WarehouseApply> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from WarehouseApply where 1=1 ");
        if (paramaMap.containsKey("yhId")) {
            sql.append(" and yhId =:yhId ");
        }
        if (paramaMap.containsKey("ckManager")) {
            sql.append(" and ckManager  =:applyStatus ");
        }
        if (paramaMap.containsKey("inStatus")) {
            sql.append(" and inStatus =:inStatus ");
        }
       super.pageByHql(sql.toString(), pageEntity, paramaMap);
        translate(pageEntity.getResults());
    }

    @Override
    protected void translate(List<WarehouseApply> list) {
        super.translate(list);
    }
}