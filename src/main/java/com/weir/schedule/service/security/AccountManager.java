/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.service.security;

import com.weir.schedule.dao.security.AccountDao;
import com.weir.schedule.model.security.Account;
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
public class AccountManager {

    @Inject
    private AccountDao dao;
    private List<Account> accounts;

    public List<?> getAll() {
        accounts = new ArrayList<Account>();
        Iterator<Account> it = dao.findAll().iterator();
        while (it.hasNext()) {
            accounts.add(it.next());
        }
        return accounts;
    }

    public void save(Account entity) throws Exception {
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

    public void remove(Account entity) throws Exception {
        try {
            dao.delete(entity);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void remove(List<? extends Account> entities) throws Exception {
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
