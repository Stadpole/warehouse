package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.Box;
import com.bupt.repository.BoxRepository;
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
public class BoxService extends BasePageService<Box, String> {
    @Autowired
    private BoxRepository boxRepository;
    @Autowired
    private BoxSizeService boxSizeService;
    public void save(Box entity) {
        boxRepository.save(entity);
    }

    public Box findById(String id) {
        return boxRepository.findOne(id);
    }
    public Box findByHgId(String id) {
        return boxRepository.findByHgId(id);
    }
    public List<Box> findByCkId(String id) {
        return boxRepository.findByCkId(id);
    }
    public List<Box>  findByCkIdAndHgStatus(String id,Integer hgStatus) {
        return boxRepository.findByCkIdAndHgStatus(id,hgStatus);
    }
    public List<Box> findAll(){return boxRepository.findAll();}
    public void deleteById(String id) {
        boxRepository.delete(id);
    }

    public void pageByHql(PageEntity<Box> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from Box where 1=1 ");
        if (paramaMap.containsKey("ckId")) {
            sql.append(" and ckId =:ckId ");
        }
        if (paramaMap.containsKey("hgStatus")) {
            sql.append(" and hgStatus =:hgStatus ");
        }
       super.pageByHql(sql.toString(), pageEntity, paramaMap);
        translate(pageEntity.getResults());
    }

    @Override
    protected void translate(List<Box> list) {
        if(list!=null) {
           for (Box box : list) {
                box.setHgSizeName(boxSizeService.findById(box.getHgSizeId()).getHgSize());
            }
        }
        super.translate(list);
    }
}