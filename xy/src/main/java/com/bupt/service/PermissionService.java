package com.bupt.service;

import com.bupt.common.base.BasePageService;
import com.bupt.common.base.PageEntity;
import com.bupt.entity.SysPermission;
import com.bupt.repository.PermissionRepository;
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
public class PermissionService extends BasePageService<SysPermission, String> {
    @Autowired
    private PermissionRepository permissionRepository;

    public void save(SysPermission entity) {
        permissionRepository.save(entity);
    }

    public SysPermission findById(Integer id){return permissionRepository.findById(id) ;}

    public void deleteById(Integer id) {
        permissionRepository.deleteById(id);
    }

    public void pageByHql(PageEntity<SysPermission> pageEntity, Map<String, Object> paramaMap) {
        StringBuilder sql = new StringBuilder(" from SysPermission where 1=1 ");
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
    protected void translate(List<SysPermission> list) {
        super.translate(list);
    }
}