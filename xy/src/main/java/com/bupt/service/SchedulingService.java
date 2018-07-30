package com.bupt.service;

import com.bupt.entity.Box;
import com.bupt.entity.Warehouse;
import com.bupt.entity.WarehouseIn;
import com.bupt.scheduling.Scheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by mengying on 2017/9/21.
 */
@Service
@Transactional
public class SchedulingService {
    @Autowired
    private  BoxService boxService;
    @Autowired
    private  WarehouseInService warehouseInService;
    @Autowired
    private  WarehouseService warehouseService;
    //获取仓库剩余库存的货柜号和仓库号，格式为 仓库号#货柜号
    public List<String> recentIn(){
        List<Box> boxList=boxService.findAll();
        ArrayList<Box> boxes=new ArrayList<>();
        ArrayList<String> list=new ArrayList<>();
        for(Box box:boxList){
            if(box.getHgStatus()==0) {
                boxes.add(box);
            }
        }
        for(Box box:boxes){
            String ss=box.getCkId()+"#"+box.getHgId();
            if(box.getHgSizeId()==0){
                addList(list,ss,5.0);
            }
           else if(box.getHgSizeId()==1){
                addList(list,ss,10.0);
            }
           else if(box.getHgSizeId()==2){
                addList(list,ss,20.0);
            }
        }
        return list;
    }

    public void addList(ArrayList<String> list,String s,Double n){
        for(int i=0;i<n;i++){
            list.add(s);
        }
    }
    //获取仓库拥有库存的货柜号和仓库号，格式为 仓库号#货柜号
    public List<String> recentOut(String spId){
        List<WarehouseIn> inList=warehouseInService.findAll();
        ArrayList<WarehouseIn> warehouseIns=new ArrayList<>();
        ArrayList<String> list=new ArrayList<>();
        for(WarehouseIn warehouseIn:inList){
            if(warehouseIn.getSpId().equals(spId)&&warehouseIn.getRkCount()>=0){
                warehouseIns.add(warehouseIn);
            }
        }
        for(WarehouseIn warehouseIn:warehouseIns){
            String ss=warehouseIn.getCkId()+"#"+warehouseIn.getHgId();
            addList(list,ss,warehouseIn.getRkCount());
        }
        return list;
    }
    //	locations为仓库所在的位置坐标，格式为 仓库号#经度#纬度
    public List<String> locations(){
        List<Warehouse> warehouseList=warehouseService.findAll();
        ArrayList<String> list=new ArrayList<>();
        for(Warehouse warehouse:warehouseList){
            String ss=warehouse.getCkId()+"#"+warehouse.getLng()+"#"+warehouse.getLat();
            list.add(ss);
        }
        return list;
    }
    //	p_loc为当前用户的位置信息，格式为 经度#纬度
    public String p_loc(){
        return "11.400738#40.896481";
    }
    public  List<String> solutionIn(double need){
        return Scheduling.Deal( need,recentIn(),locations(),p_loc());
    }
    public  List<String> solutionOut(double need,String spId){
        return Scheduling.Deal( need,recentOut(spId),locations(),p_loc());
    }
}