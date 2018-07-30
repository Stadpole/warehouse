package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.Inventory;
import com.bupt.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by mengying on 2017/9/21.
 */
@Service
@Transactional
public class InventoryService extends BasePageService<Inventory, String> {
    @Autowired
    private InventoryRepository inventoryRepository;

    public void save(Inventory entity) {
        inventoryRepository.save(entity);
    }

    public Inventory findById(Integer id) {
        return inventoryRepository.findById(id);
    }

    public void deleteById(Integer id) {
        inventoryRepository.deleteById(id);
    }

    public void pageByHql(PageEntity<Inventory> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from Inventory where 1=1 ");
       /* if (paramaMap.containsKey("name")) {
            sql.append(" and name =:name ");
        }
        if (paramaMap.containsKey("power")) {
            sql.append(" and power =:power ");
        }*/
       super.pageByHql(sql.toString(), pageEntity, paramaMap);
        translate(pageEntity.getResults());
    }

    @Override
    protected void translate(List<Inventory> list) {
        super.translate(list);
    }
}