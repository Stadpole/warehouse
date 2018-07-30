package com.bupt.repository;

import com.bupt.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface PermissionRepository extends JpaRepository<SysPermission, String> {
    @Query
    SysPermission findById(Integer id);
    @Query
    void deleteById(Integer id);

}
