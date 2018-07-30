package com.bupt.repository;

import com.bupt.entity.WarehouseOutApply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface WarehouseOutApplyRepository extends JpaRepository<WarehouseOutApply, String> {
public WarehouseOutApply findByHgId(String hgId);
public List<WarehouseOutApply> findAllByCkId(String ckId);
}
