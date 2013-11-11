/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.service.security;

import com.weir.schedule.dao.security.AuthorityDao;
import com.weir.schedule.model.security.Authority;
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
public class AuthorityManager {

    @Inject
    private AuthorityDao dao;
    private List<Authority> entities;

    public List<?> getAll() {
        entities = new ArrayList<Authority>();
        Iterator<Authority> it = dao.findAll().iterator();
        while (it.hasNext()) {
            entities.add(it.next());
        }
        return entities;
    }

    public void save(Authority entity) throws Exception {
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

    public void remove(Authority entity) throws Exception {
        try {
            dao.delete(entity);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void remove(List<? extends Authority> entities) throws Exception {
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
