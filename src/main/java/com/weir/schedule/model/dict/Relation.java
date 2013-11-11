/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model.dict;

import com.weir.schedule.model.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Weir
 */
@Entity
@Table(name = "dict_relation")
public class Relation extends BaseEntity {

    private static final long serialVersionUID = -1346729200917315L;
    private String code;
    private String name;

    public Relation() {
    }

    public Relation(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
