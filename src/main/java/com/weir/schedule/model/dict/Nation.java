/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model.dict;

import com.weir.schedule.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 民族代码
 *
 * @author Weir
 */
@Entity
@Table(name = "dict_nation")
public class Nation extends BaseEntity {

    private static final long serialVersionUID = -13467292079173155L;
    @Column(nullable = false, unique = true)
    private String code;
    private String name;

    public Nation() {
    }

    public Nation(String code, String name) {
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
