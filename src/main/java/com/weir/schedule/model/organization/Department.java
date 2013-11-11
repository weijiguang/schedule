/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model.organization;

import com.weir.schedule.model.BaseEntity;
import com.weir.schedule.model.security.Account;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Weir
 */
@Entity
@Table(name = "org_department")
public class Department extends BaseEntity {

    private static final long serialVersionUID = -113447292913155L;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne
    private Personnel head;
    @ManyToOne
    private Department parent;
    private String hierarchy;
    private String description;
    private Date createTime;
    @ManyToOne
    private Account creator;

    public Department() {
    }

    public Department(String code, String name, Personnel head, Department parent, String hierarchy, String description, Date createTime, Account creator) {
        this.code = code;
        this.name = name;
        this.head = head;
        this.parent = parent;
        this.hierarchy = hierarchy;
        this.description = description;
        this.createTime = createTime;
        this.creator = creator;
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

    public Personnel getHead() {
        return head;
    }

    public void setHead(Personnel head) {
        this.head = head;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }
}
