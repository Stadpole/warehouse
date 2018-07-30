package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.LocationDistrict;
import com.bupt.repository.LocationDistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by mengying on 2017/9/21.
 */
@Service
@Transactional

public class LocationDistrictService extends BasePageService<LocationDistrict, String> {
    @Autowired
    private LocationDistrictRepository locationDistrictRepository;

    public void save(LocationDistrict entity) {
        locationDistrictRepository.save(entity);
    }

    public LocationDistrict findOne(Integer id) {
        LocationDistrict entity= locationDistrictRepository.findById(id);
        return entity;
    }
    public List<LocationDistrict> findAll(){
        return locationDistrictRepository.findAll();
    }

    public List<LocationDistrict> findByParentId(Integer id) {
        List<LocationDistrict> entity= locationDistrictRepository.findByParentId(id);
       return entity;
    }
    public void deleteById(Integer id) {
        locationDistrictRepository.deleteById(id);
    }
    public LocationDistrict findByDistrict(String district) {
        return locationDistrictRepository.findByName(district);
    }

    public void pageByHql(PageEntity<LocationDistrict> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from LocationDistrict where 1=1 ");
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
    protected void translate(List<LocationDistrict> lists) {
        super.translate(lists);
    }
}