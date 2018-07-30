package com.bupt.controller;

import com.bupt.common.base.BaseCommonController;

import com.bupt.entity.Account;
import com.bupt.entity.Commodity;
import com.bupt.entity.ResultObject;
import com.bupt.entity.Warehouse;
import com.bupt.service.CommodityService;
import com.bupt.service.WarehouseInService;
import com.bupt.service.WarehouseOutService;
import com.bupt.service.WarehouseService;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/check")
public class CheckController extends BaseCommonController {
    @Autowired
    private WarehouseInService warehouseInService;
    @Autowired
    private WarehouseOutService warehouseOutService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private CommodityService commodityService;
    //按季度查看出入库总量
    @RequestMapping(value = "/getQuarter", method = RequestMethod.GET)
    public Object[] getQuarter() {
        Gson gson=new Gson();
        try{
           List<Account> rkAccount=warehouseInService.findQuarter();
            List<Account> outAccount=warehouseOutService.findQuarter();
          Object [] ss=new Object[8];
         int s=0;
            for(Account account:rkAccount){
                Object [] objects=new Object[2];
                objects[0]=account.getDate();
                objects[1]=account.getQuantity();
                ss[s]=objects;
                s++;
            }
            for(Account account:outAccount){
                Object [] objects=new Object[2];
                objects[0]=account.getDate();
                objects[1]=account.getQuantity();

                ss[s]=objects;
                s++;
            }
            return ss;

        }catch (Exception e){
            return null;
        }

    }
    //按月查看出入库总量
    @RequestMapping(value = "/getMonth", method = RequestMethod.GET)
    public  Object[]  getMonth() {
         try{
           List<Account> rkAccount=warehouseInService.findMonth();
           List<Account> outAccount=warehouseOutService.findMonth();
            Object [] ss=new Object[24];
            int s=0;
            for(Account account:rkAccount){
                Object [] objects=new Object[2];
                objects[0]=account.getDate();
                objects[1]=account.getQuantity();
                ss[s]=objects;
                s++;
            }
            for(Account account:outAccount){
                Object [] objects=new Object[2];
                objects[0]=account.getDate();
                objects[1]=account.getQuantity();
                ss[s]=objects;
                s++;
            }
            return ss;
        }catch (Exception e){
            return null;
        }

    }
    //按日查看入库总量
    @RequestMapping(value = "/getDay", method = RequestMethod.GET)
    public Object[] getDayIn(Date start,Date end) {
        Gson gson=new Gson();
        try{
            long difference =  (start.getTime()-end.getTime())/86400000;
           int days=(int)Math.abs(difference)+1;
           System.out.print(days);
            Object [] ss=new Object[days*2];
           List<Account> rkAccount=warehouseInService.findDay(start,end);
            List<Account> outAccount=warehouseOutService.findDay(start,end);
            int s=0;
            for(Account account:rkAccount){
                Object [] objects=new Object[2];
                objects[0]=account.getDate();
                objects[1]=account.getQuantity();
                ss[s]=objects;
                s++;
            }
            for(Account account:outAccount){
                Object [] objects=new Object[2];
                objects[0]=account.getDate();
                objects[1]=account.getQuantity();
                ss[s]=objects;
                s++;
            }
            return  ss;
        }catch (Exception e){
            return null;
        }

    }

    //查看仓库在某个时间段某类商品的出入库量
    @RequestMapping(value = "/getCkSp", method = RequestMethod.GET)
    public String getCkSp(String ckId,String spId,Date start,Date end) {
    try{
        Gson gson=new Gson();
        Account account=warehouseInService.findSpRkSum(ckId, spId, start, end);
        Account account1=warehouseOutService.findSpRkSum(ckId, spId, start, end);
        ResultObject resultObject = new ResultObject();
        Warehouse warehouse=warehouseService.findByCkId(ckId);
        Commodity commodity=commodityService.findBySpId(spId);

        if(warehouse!=null) {
            resultObject.setCkName(warehouse.getName());
        }
        if(commodity!=null){
            resultObject.setSpName(commodity.getSpName());
        }
        if(account==null){
            resultObject.setRkCount(0.0);
        }
        if(account!=null&&account.getQuantity()==null){
            resultObject.setRkCount(0.0);
        }
        if(account!=null&&account.getQuantity()!=null){
            resultObject.setRkCount(account.getQuantity());
        }
        if(account1==null){
            resultObject.setOutCount(0.0);
        }
        if(account1!=null&&account1.getQuantity()==null){
            resultObject.setOutCount(0.0);
        }
        if(account1!=null&&account1.getQuantity()!=null){
            resultObject.setOutCount(account1.getQuantity());
        }

        resultObject.setSpType(commodity.getSpType());
        resultObject.setCkId(ckId);
        resultObject.setSpId(spId);
        resultObject.setTotalCount(resultObject.getRkCount()-resultObject.getOutCount());
        resultObject.setUnit("个");
        return gson.toJson(resultObject);
        }catch (Exception e){
           return sendFailMessage();
        }

    }

}

