package com.bupt.controller;


import com.bupt.common.base.BaseCommonController;
import com.bupt.common.enums.BuildingEnum;
import com.bupt.entity.*;
import com.bupt.service.*;
import com.google.gson.Gson;
import com.sun.tools.corba.se.idl.ExceptionEntry;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleList;
import java.util.*;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/list")
public class IdsListController extends BaseCommonController {
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private WarehouseInService warehouseInService;
    @Autowired
    private BoxService boxService;
    @Autowired
    private BoxSizeService boxSizeService;
    @Autowired
    private UserInfoService userInfoService;
//级联查询，所有仓库以及对应的货柜
    @RequestMapping(value = "/ckIds", method = RequestMethod.GET)
    public String getCkIds() {
        List<Warehouse> list=warehouseService.findAll();
        Gson gson=new Gson();
        ArrayList<WarehouseAndBox> arrayList=new ArrayList<>();
        try{
            for(Warehouse warehouse:list){
                WarehouseAndBox warehouseAndBox=new WarehouseAndBox();
                warehouseAndBox.setValue(warehouse.getCkId());
                warehouseAndBox.setLabel(warehouse.getName());
                List<Box> boxes=boxService.findByCkId(warehouse.getCkId());
                List<BoxSmall> boxSmalls=new ArrayList<>();
                for(Box box:boxes){
                    BoxSmall boxSmall=new BoxSmall();
                    if(box.getHgStatus()==0) {
                        BoxSize boxSize=boxSizeService.findById(box.getHgSizeId());
                        boxSmall.setValue(box.getHgId());
                        boxSmall.setLabel(box.getHgName()+" "+boxSize.getHgSize());
                        boxSmalls.add(boxSmall);
                    }
                }
                warehouseAndBox.setChildren(boxSmalls);
                arrayList.add(warehouseAndBox);
            }
            return gson.toJson(arrayList);
        }catch (Exception e){
            return sendFailMessage();
        }
    }
    //查询商品
    @RequestMapping(value = "/spIds", method = RequestMethod.GET)
    public String getSpIds() {
        Gson gson=new Gson();
        List<Commodity> list=commodityService.findAll();
       ArrayList<CommoditySelect> arrayList=new ArrayList<>();
        try{
            for(Commodity commodity:list){
              CommoditySelect commoditySelect=new CommoditySelect();
              commoditySelect.setValue(commodity.getSpId());
              commoditySelect.setLabel(commodity.getSpName()+" "+commodity.getSpBrand());
              arrayList.add(commoditySelect);
            }
            return  gson.toJson(arrayList);
        }catch (Exception e){
            return sendFailMessage();
        }
    }
    //查询商品下拉框
    @RequestMapping(value = "/ckId", method = RequestMethod.GET)
    public String getCkId() {
        List<Warehouse> list=warehouseService.findAll();
        ArrayList<WarehouseAndBox> arrayList=new ArrayList<>();
        Gson gson=new Gson();
        try{
            for(Warehouse warehouse:list){
                WarehouseAndBox warehouseAndBox=new WarehouseAndBox();
                warehouseAndBox.setValue(warehouse.getCkId());
                warehouseAndBox.setLabel(warehouse.getName());
                arrayList.add(warehouseAndBox);
            }
            return gson.toJson(arrayList);
        }catch (Exception e){
            return sendFailMessage();
        }
    }
    //货柜大小下拉框
    @RequestMapping(value = "/hgSize", method = RequestMethod.GET)
    public String getHgSize() {
        Gson gson=new Gson();
        ArrayList<BoxSizeName> arrayList=new ArrayList<>();
        try{
            for(int i=0;i<3;i++){
                BoxSize boxSize=boxSizeService.findById(i);
                BoxSizeName boxSizeName=new BoxSizeName();
                boxSizeName.setValue(i);
                boxSizeName.setLabel(boxSize.getHgSize());
                arrayList.add(boxSizeName);
            }
            return gson.toJson(arrayList);
        }catch (Exception e){
            return sendFailMessage();
        }
    }
    //选择仓库管理员下拉框
    //@RequestMapping(value = "/ckManager", method = RequestMethod.GET)
    @Test
    public void getCkManager() {
        Gson gson=new Gson();
        ArrayList<CkManager> arrayList=new ArrayList<>();
        try{
           List<UserInfo> userInfos=userInfoService.findAll();
           for(UserInfo userInfo:userInfos){
              List<SysRole> sysRoles=userInfo.getRoleList();
              if(sysRoles!=null){
                  for(SysRole sysRole:sysRoles){
                      if(sysRole.getId()==1){
                          CkManager ckManager=new CkManager();
                          ckManager.setValue(userInfo.getUid());
                          ckManager.setLabel(userInfo.getUsername()+" "+userInfo.getJob());
                          arrayList.add(ckManager);
                      }
                          break;
                  }
              }
           }
          System.out.print(gson.toJson(arrayList));
        }catch (Exception e){
            System.out.print("fail");
        }
    }
    //级联查询，所有仓库以及对应的商品
    @RequestMapping(value = "/ckAndSp", method = RequestMethod.GET)
    public String getCkAndSp() {
        List<Warehouse> list=warehouseService.findAll();
        ArrayList<WarehouseAndSps> arrayList=new ArrayList<>();
        Gson gson=new Gson();
        try{
            for(Warehouse warehouse:list){
                ArrayList<CommoditySelect> arrayList1=new ArrayList<>();
                WarehouseAndSps warehouseAndSps=new WarehouseAndSps();
                warehouseAndSps.setValue(warehouse.getCkId());
                warehouseAndSps.setLabel(warehouse.getName());
              List<WarehouseIn> warehouseInList=warehouseInService.findAllByCkId(warehouse.getCkId());
              if(warehouseInList!=null){
                  Set<String> set =new HashSet<>();
                  for(WarehouseIn warehouseIn:warehouseInList){
                    set.add(warehouseIn.getSpId());
                  }
                  Iterator<String> iterator=set.iterator();
                  while(iterator.hasNext()){
                      CommoditySelect commoditySelect=new CommoditySelect();
                      String index=iterator.next();
                      commoditySelect.setValue(index);
                      Commodity commodity=commodityService.findBySpId(index);
                      System.out.print(commodity);
                      if(commodity!=null) {
                          commoditySelect.setLabel(commodity.getSpName());
                      }
                      arrayList1.add(commoditySelect);
                  }

              }
               warehouseAndSps.setChildren(arrayList1);
                arrayList.add(warehouseAndSps);
            }
            return gson.toJson(arrayList);
        }catch (Exception e){
            return sendFailMessage();
        }
    }
    //级联查询，查询楼层下房间
    @RequestMapping(value = "/floor", method = RequestMethod.GET)
    public String getFloor() {
        Gson gson=new Gson();
      return null;
    }

}

