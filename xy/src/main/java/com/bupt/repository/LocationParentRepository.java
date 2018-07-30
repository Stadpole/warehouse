package com.bupt.repository;

import com.bupt.entity.LocationParent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface LocationParentRepository extends JpaRepository<LocationParent, String> {
void deleteById(Integer id);
 LocationParent findById(Integer id);
 }
