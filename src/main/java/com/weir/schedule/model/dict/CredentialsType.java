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
@Table(name = "dict_credentialstype")
public class CredentialsType extends BaseEntity {

    private static final long serialVersionUID = -1013447223693155L;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false, unique = true)
    private String name;
    private String credentialsNoValidateClass;

    public CredentialsType() {
    }

    public CredentialsType(String code, String name, String credentialsNoValidateClass) {
        this.code = code;
        this.name = name;
        this.credentialsNoValidateClass = credentialsNoValidateClass;
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

    public String getCredentialsNoValidateClass() {
        return credentialsNoValidateClass;
    }

    public void setCredentialsNoValidateClass(String credentialsNoValidateClass) {
        this.credentialsNoValidateClass = credentialsNoValidateClass;
    }
}
