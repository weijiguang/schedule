/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model.security;

import com.weir.schedule.model.BaseEntity;
import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 账号（用户）信息
 *
 * @author Weir
 */
@Entity
@Table(name = "sys_account")
public class Account extends BaseEntity {

    private static final long serialVersionUID = -113442553155L;
    @Column(nullable = false, unique = true)
    private String name;
    private String password;
    private String salt;
    private Date firstLoginTime;
    private Date lastLoginTime;
    private int loginCount;
    private Date createTime;
    @ManyToOne
    private Account creator;
    private boolean enable;
    private String description;
    @ManyToMany
    private List<Role> roles;
    @ManyToMany
    private List<Permission> permissions;

    public Account() {
    }

    public Account(String name, String password, String salt, String description) {
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.description = description;
    }

    public Account(String name, String password, String salt, String description, List<Role> roles, List<Permission> permissions) {
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.description = description;
        this.roles = roles;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
