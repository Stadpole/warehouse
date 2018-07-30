package com.bupt.repository;

import com.bupt.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface WarehouseRepository extends JpaRepository<Warehouse, String> {
 Page<Warehouse> findByCreateTimeBetween(Date str1, Date str2, Pageable pageable);
 Warehouse findByCkId(String ckId);
 List<Warehouse> findByParentId(Integer parentId);
 void deleteByCkId(String ckId);
 }
