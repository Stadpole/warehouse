package com.bupt.repository;

import com.bupt.entity.Box;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface BoxRepository extends JpaRepository<Box, String> {
public Box findByHgId(String hgId);
public List<Box> findByCkId(String ckId);
public List<Box> findByCkIdAndHgStatus(String ckId,Integer hgStatus);
}
