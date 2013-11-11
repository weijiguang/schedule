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
 *
 * @author Weir
 */
@Entity
@Table(name = "dict_country")
public class Country extends BaseEntity {

    private static final long serialVersionUID = -113464729209173155L;
    @Column(nullable = false, unique = true)
    private String code;
    private String name;
    private String description;

    public Country() {
    }

    public Country(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
