package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.SysRole;
import com.bupt.repository.RoleRepository;
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
public class RoleService extends BasePageService<SysRole, String> {
    @Autowired
    private RoleRepository roleRepository;

    public void save(SysRole entity) {
        roleRepository.save(entity);
    }

    public SysRole findById(Integer id){return roleRepository.findById(id) ;}

    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }

    public void pageByHql(PageEntity<SysRole> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from SysRole where 1=1 ");
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
    protected void translate(List<SysRole> list) {
        super.translate(list);
    }
}