package com.bupt.repository;

import com.bupt.entity.WarehouseApply;
import com.bupt.entity.WarehouseIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface WarehouseApplyRepository extends JpaRepository<WarehouseApply, String> {
public WarehouseApply findByHgId(String hgId);
public List<WarehouseApply> findAllByCkId(String ckId);
}
