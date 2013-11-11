/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model.dict;

import com.weir.schedule.model.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 行业、职业信息
 *
 * @author Weir
 */
@Entity
@Table(name = "dict_profession")
public class Profession extends BaseEntity {

    private static final long serialVersionUID = -134672920079173155L;
    private String code;
    private String name;
    @ManyToOne
    private Profession parent;
    private String hierarchy;//层次关系

    public Profession() {
    }

    public Profession(String code, String name, Profession parent, String hierarchy) {
        this.code = code;
        this.name = name;
        this.parent = parent;
        this.hierarchy = hierarchy;
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

    public Profession getParent() {
        return parent;
    }

    public void setParent(Profession parent) {
        this.parent = parent;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }
}
