package com.bupt.repository;

import com.bupt.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface CommodityRepository extends JpaRepository<Commodity, String> {
Commodity findBySpId(String spId);
}
