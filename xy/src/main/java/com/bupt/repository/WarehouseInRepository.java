package com.bupt.repository;

import com.bupt.entity.WarehouseIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface WarehouseInRepository extends JpaRepository<WarehouseIn, String> {
public WarehouseIn findByHgId(String hgId );
public List<WarehouseIn> findAllByCkId(String ckId );
public WarehouseIn findByCkId(String ckId );
public List<WarehouseIn> findBySpIdAndCkId(String spId,String ckId );
}
