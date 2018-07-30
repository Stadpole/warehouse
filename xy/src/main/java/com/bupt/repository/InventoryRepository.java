package com.bupt.repository;

import com.bupt.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stadpole on 2017/9/21.
 */
public interface InventoryRepository extends JpaRepository<Inventory, String> {
 void deleteById(Integer id);
 Inventory findById(Integer id);
}
