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
 * 职务信息 职务是一个人坐得位子，比如副科长，科长，副处长，处长，副局长，局长等等。
 * 职称是一个人的业务能力达到水平的评价，比如，助理工程师，工程师，高级工程师等。
 *
 * @author Weir
 */
@Entity
@Table(name = "dict_duty")
public class Duty extends BaseEntity {

    private static final long serialVersionUID = -101344722493155L;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false, unique = true)
    private String name;

    public Duty() {
    }

    public Duty(String code, String name) {
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
