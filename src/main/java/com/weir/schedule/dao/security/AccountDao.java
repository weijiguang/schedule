/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.dao.security;

import com.weir.schedule.model.security.Account;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Weir
 */
public interface AccountDao extends CrudRepository< Account, Long>{
    
}
