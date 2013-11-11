/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model.inhospital;

import com.weir.schedule.model.BaseEntity;
import com.weir.schedule.model.dict.AgeUnit;
import com.weir.schedule.model.dict.Country;
import com.weir.schedule.model.dict.Gender;
import com.weir.schedule.model.dict.Marriage;
import com.weir.schedule.model.dict.Nation;
import com.weir.schedule.model.dict.Profession;
import com.weir.schedule.model.dict.Relation;
import com.weir.schedule.model.dict.WeightUnit;
import com.weir.schedule.model.organization.Department;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Weir
 */
@Entity
@Table(name = "inh_patient")
public class Patient extends BaseEntity {

    private static final long serialVersionUID = -1834472293155L;
    private String code;
    private String name;
    @ManyToOne
    private Gender gender;
    private Date birth;
    private int age;
    @ManyToOne
    private AgeUnit ageUnit;
    @ManyToOne
    private Country nationality;//国籍
    private int weight;
    @ManyToOne
    private WeightUnit weightUnit;
    private String nativePlace;//籍贯
    private String birthplace;//出生地
    @ManyToOne
    private Nation nation;//民族
    private String idNo;//身份证号  
    @ManyToOne
    private Profession profession;//职业
    @ManyToOne
    private Marriage marriage;
    private String address;//住址
    private String addressPhone;//住址电话
    private String addressZip;//住址邮编
    private String accountAddress;//户口地址
    private String workAddress;//工作单位地址
    private String workPhone;//工作电话
    private String workZip;//工作邮编
    private String contact;//联系人
    @ManyToOne
    private Relation relation;
    private String contactAddress;
    private String contactPhone;
    private String email;
    @ManyToOne
    private Department department;
}
