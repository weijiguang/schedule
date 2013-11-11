/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model.organization;

import com.weir.schedule.model.BaseEntity;
import com.weir.schedule.model.dict.CredentialsType;
import com.weir.schedule.model.dict.Duty;
import com.weir.schedule.model.dict.Gender;
import com.weir.schedule.model.dict.JobTitle;
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
@Table(name = "org_personnel")
public class Personnel extends BaseEntity {

    private static final long serialVersionUID = -1134472293155L;
    @Column(nullable = false, unique = true)
    private String code;
    @ManyToOne
    private CredentialsType credentialsType;//证件类型
    private String credentialsNo;//证件号码
    private String name;
    @ManyToOne
    private Gender gender;
    private Date birth;
    private String mobile;
    private String workPhone;
    private String address;//住址
    private String telePhone;//电话
    private String zip;//邮编
    private String contact;//联系人
    private String contactPhone;//联系人电话
    private String email;
    private String pinyinCode;
    private String wubiCode;
    @ManyToOne
    private Department department;
    @ManyToOne
    private JobTitle jobTitle;//职称
    @ManyToOne
    private Duty duty;//职务

    public Personnel() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CredentialsType getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(CredentialsType credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsNo() {
        return credentialsNo;
    }

    public void setCredentialsNo(String credentialsNo) {
        this.credentialsNo = credentialsNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }

    public String getWubiCode() {
        return wubiCode;
    }

    public void setWubiCode(String wubiCode) {
        this.wubiCode = wubiCode;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Duty getDuty() {
        return duty;
    }

    public void setDuty(Duty duty) {
        this.duty = duty;
    }
}
