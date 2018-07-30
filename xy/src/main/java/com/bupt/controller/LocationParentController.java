package com.bupt.controller;

import com.bupt.common.base.BaseCommonController;
import com.bupt.common.base.PageEntity;
import com.bupt.common.utils.BeanUtills;
import com.bupt.common.utils.NumberUtills;
import com.bupt.entity.LocationDistrict;
import com.bupt.entity.LocationParent;
import com.bupt.entity.Warehouse;
import com.bupt.entity.WarehouseParent;
import com.bupt.service.LocationDistrictService;
import com.bupt.service.LocationParentService;
import com.bupt.service.WarehouseParentService;
import com.bupt.service.WarehouseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stadpole on 2017/9/21.
 */
@RestController
@RequestMapping(value = "/locationParent")
public class LocationParentController extends BaseCommonController {
    @Autowired
    private LocationParentService locationParentService;
    @Autowired
    private LocationDistrictService locationDistrictService;
    @Autowired
    private WarehouseParentService warehouseParentService;
    @Autowired
    private WarehouseService warehouseService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @RequiresPermissions("warehouse:manage")//权限管理;
    public String save(@RequestBody LocationParent entity) {
        return sendFailMessage();

    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @RequiresPermissions("warehouse:manage")//权限管理;
    public List<LocationParent> findAll() {
        List<LocationParent> list = locationParentService.findAll();
        for (LocationParent locationParent : list) {
            Double cityCount=0.0;
            String location0 = locationParent.getLng() + "," + locationParent.getLat();
            locationParent.setCenter(location0);
            List<LocationDistrict> locationDistricts = locationDistrictService.findByParentId(locationParent.getId());
           //循环遍历某个区，找到属于这个区的仓库信息
            for (LocationDistrict locationDistrict : locationDistricts) {
                String location2 = locationDistrict.getLng() + "," + locationDistrict.getLat();
                locationDistrict.setCenter(location2);
                Double districtCount = 0.0;
                List<WarehouseParent> warehouseParents = warehouseParentService.findByParentId(locationDistrict.getId());
                if(warehouseParents!=null){
                for (WarehouseParent warehouseParent : warehouseParents) {
                    Double tempCount = warehouseParent.getCount();
                    districtCount = districtCount + tempCount;
                    String location1 = warehouseParent.getLng() + "," + warehouseParent.getLat();
                    warehouseParent.setCenter(location1);
                    List<Warehouse> list1 = warehouseService.findByParentId(warehouseParent.getId());
                        for (Warehouse warehouse : list1) {
                            String location = warehouse.getLng() + "," + warehouse.getLat();
                            warehouse.setCenter(location);
                            if (warehouse.getCkStatus() == 0) {
                                warehouse.setCkStatusName("可用");
                            } else if (warehouse.getCkStatus() == 1) {
                                warehouse.setCkStatusName("不可用");
                            }
                        }
                    warehouseParent.setSubDistricts(list1);
                }
                }
                locationDistrict.setCount(districtCount);
                locationDistrict.setSubDistricts(warehouseParents);
                double tempCityCount = locationDistrict.getCount();
                cityCount = cityCount + tempCityCount;
                locationDistrictService.save(locationDistrict);
            }

            locationParent.setSubDistricts(locationDistricts);
            locationParent.setCount(cityCount);
            locationParentService.save(locationParent);
        }
        return list;
    }
}