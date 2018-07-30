package com.bupt.repository;

import com.bupt.entity.WarehouseOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface WarehouseOutRepository extends JpaRepository<WarehouseOut, String> {
    public List<WarehouseOut> findAllByCkId(String ckId );
    WarehouseOut findByHgId(String hgId);

    public List<WarehouseOut> findBySpIdAndCkId(String spId,String ckId);
}
