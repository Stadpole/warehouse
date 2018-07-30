package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.Commodity;
import com.bupt.repository.CommodityRepository;
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
public class CommodityService extends BasePageService<Commodity, String> {
    @Autowired
    private CommodityRepository commodityRepository;

    public void save(Commodity entity) {
        commodityRepository.save(entity);
    }

    public Commodity findById(String id) {
        return commodityRepository.findOne(id);
    }
    public Commodity findBySpId(String spId) {
        return commodityRepository.findBySpId(spId);
    }
    public List<Commodity> findAll(){return commodityRepository.findAll();}

    public void deleteById(String id) {
        commodityRepository.delete(id);
    }

    public void pageByHql(PageEntity<Commodity> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from Commodity where 1=1 ");
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
    protected void translate(List<Commodity> list) {
        super.translate(list);
    }
}