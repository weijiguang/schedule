/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.dao;

import com.weir.schedule.model.BaseEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Weir
 */
public interface BaseDao extends CrudRepository<BaseEntity, Long> {
}
