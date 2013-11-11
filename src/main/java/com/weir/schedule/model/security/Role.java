/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model.security;

import com.weir.schedule.model.BaseEntity;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Weir
 */
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = -11344253155L;
    private String name;
    private String description;
    @ManyToMany
    private List<Permission> permissions;
    @ManyToMany(mappedBy = "roles")
    private List<Account> accounts;

    public Role() {
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role(String name, String description, List<Permission> permissions) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;
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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
