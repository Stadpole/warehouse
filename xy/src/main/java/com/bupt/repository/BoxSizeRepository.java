package com.bupt.repository;

import com.bupt.entity.BoxSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface BoxSizeRepository extends JpaRepository<BoxSize, String> {
    @Query
    BoxSize findByHgSizeId(Integer hgSizeId);
}
