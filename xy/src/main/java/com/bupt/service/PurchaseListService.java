package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.PurchaseList;
import com.bupt.repository.PurchaseListRepository;
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
public class PurchaseListService extends BasePageService<PurchaseList, String> {
    @Autowired
    private PurchaseListRepository purchaseListRepository;

    public void save(PurchaseList entity) {
        purchaseListRepository.save(entity);
    }

    public PurchaseList findById(String id) {
        return purchaseListRepository.findOne(id);
    }

    public void deleteById(String id) {
        purchaseListRepository.delete(id);
    }

    public void pageByHql(PageEntity<PurchaseList> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from PurchaseList where 1=1 ");
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
    protected void translate(List<PurchaseList> list) {
        super.translate(list);
    }
}