package com.bupt.repository;

import com.bupt.entity.WarehouseParent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface WarehouseParentRepository extends JpaRepository<WarehouseParent, String> {
void deleteById(Integer id);
 WarehouseParent findById(Integer id);
 WarehouseParent findByName(String name);
 List<WarehouseParent> findByParentId(Integer parentId);
 }
