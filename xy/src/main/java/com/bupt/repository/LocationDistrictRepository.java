package com.bupt.repository;

import com.bupt.entity.LocationDistrict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface LocationDistrictRepository extends JpaRepository<LocationDistrict, String> {
void deleteById(Integer id);
 LocationDistrict findById(Integer id);
 LocationDistrict findByName(String name);
 List<LocationDistrict> findByParentId(Integer parentId);
 }
