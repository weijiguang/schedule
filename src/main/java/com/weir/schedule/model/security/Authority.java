/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model.security;

import com.weir.schedule.model.BaseEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Weir
 */
@Entity
@Table(name = "sys_authority")
public class Authority extends BaseEntity {

    private static final long serialVersionUID = -134453155L;
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    @ManyToMany(mappedBy = "authorities")
    private List<Permission> permissions;

    public Authority() {
    }

    public Authority(String name, String description) {
        this.name = name;
        this.description = description;
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
