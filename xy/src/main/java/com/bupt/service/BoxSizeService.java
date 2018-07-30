package com.bupt.service;

import com.bupt.common.base.BasePageService;

import com.bupt.common.base.PageEntity;
import com.bupt.entity.BoxSize;
import com.bupt.repository.BoxSizeRepository;
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
public class BoxSizeService extends BasePageService<BoxSize, String> {
    @Autowired
    private BoxSizeRepository boxSizeRepository;

    public void save(BoxSize entity) {
        boxSizeRepository.save(entity);
    }

    public BoxSize findById(Integer id) {
        return boxSizeRepository.findByHgSizeId(id);
    }

    public void deleteById(String id) {
        boxSizeRepository.delete(id);
    }

    public void pageByHql(PageEntity<BoxSize> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from BoxSize where 1=1 ");
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
    protected void translate(List<BoxSize> list) {
        super.translate(list);
    }
}