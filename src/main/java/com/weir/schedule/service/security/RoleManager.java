/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.service.security;

import com.weir.schedule.dao.security.RoleDao;
import com.weir.schedule.model.security.Role;
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
public class RoleManager {

    @Inject
    private RoleDao dao;
    private List<Role> entities;

    public List<?> getAll() {
        entities = new ArrayList<Role>();
        Iterator<Role> it = dao.findAll().iterator();
        while (it.hasNext()) {
            entities.add(it.next());
        }
        return entities;
    }

    public void save(Role entity) throws Exception {
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

    public void remove(Role entity) throws Exception {
        try {
            dao.delete(entity);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void remove(List<? extends Role> entities) throws Exception {
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
