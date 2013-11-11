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
@Table(name = "sys_permission")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = -1134453155L;
    @Column(nullable = false, unique = true)
    private String name;
    private String decsription;
    @ManyToMany
    private List<Authority> authorities;
    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
    @ManyToMany(mappedBy = "permissions")
    private List<Account> accounts;

    public Permission() {
    }

    public Permission(String name, String decsription) {
        this.name = name;
        this.decsription = decsription;
    }

    public Permission(String name, String decsription, List<Authority> authorities) {
        this.name = name;
        this.decsription = decsription;
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecsription() {
        return decsription;
    }

    public void setDecsription(String decsription) {
        this.decsription = decsription;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
