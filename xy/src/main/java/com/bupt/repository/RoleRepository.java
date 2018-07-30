package com.bupt.repository;

import com.bupt.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface RoleRepository extends JpaRepository<SysRole, String> {
    @Query
    SysRole findById(Integer id);
    @Query
    void deleteById(Integer id);

}
