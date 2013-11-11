/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.service;

import com.weir.schedule.dao.BaseDao;
import com.weir.schedule.model.BaseEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Weir
 */
@Named
@Transactional
public class BaseManager {

    @Inject
    private BaseDao dao;
    private List<BaseEntity> entities;

    public List<?> getAll() {
        entities = new ArrayList<BaseEntity>();
        Iterator<BaseEntity> it = dao.findAll().iterator();
        while (it.hasNext()) {
            entities.add(it.next());
        }
        return entities;
    }

    public void save(BaseEntity entity) throws Exception {
        try {
            dao.save(entity);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void remove(Long id) throws Exception {
        try {
            dao.delete(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void remove(BaseEntity entity) throws Exception {
        try {
            dao.delete(entity);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void remove(List<? extends BaseEntity> entities) throws Exception {
        try {
            dao.delete(entities);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void removeAll() throws Exception {
        try {
            dao.deleteAll();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
