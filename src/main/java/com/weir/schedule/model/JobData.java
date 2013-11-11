/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model;

import com.weir.schedule.application.Constant;
import com.weir.schedule.dao.SwapDataJob;
import java.io.Serializable;

/**
 * 任务信息类
 *
 * @author Weir
 */
public class JobData implements Serializable {

    public enum State {

        BLOCKED, COMPLETE, ERROR, NONE, NORMAL, PAUSED
    }
    private String name;
    private String group;
    private String description;
    private Class jobClass = Constant.JOB_CLASS;
    private String fullName;
    private String cronex;
    private State state;
//    private Map<String, String> jobData = new HashMap<String, String>();
    private String conditionSQL;
    private String sourceSQL;
    private String targetSQL;

    public JobData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class getJobClass() {
        return jobClass;
    }

    public void setJobClass(Class jobClass) {
        this.jobClass = jobClass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCronex() {
        return cronex;
    }

    public void setCronex(String cronex) {
        this.cronex = cronex;
    }

//    public Map<String, String> getJobData() {
//        return jobData;
//    }
//
//    public void setJobData(Map<String, String> jobData) {
//        this.jobData = jobData;
//    }
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getConditionSQL() {
        return conditionSQL;
    }

    public void setConditionSQL(String conditionSQL) {
        this.conditionSQL = conditionSQL;
    }

    public String getSourceSQL() {
        return sourceSQL;
    }

    public void setSourceSQL(String sourceSQL) {
        this.sourceSQL = sourceSQL;
    }

    public String getTargetSQL() {
        return targetSQL;
    }

    public void setTargetSQL(String targetSQL) {
        this.targetSQL = targetSQL;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        JobData that = (JobData) obj;

        return null == this.getName() ? false : this.getName().equals(that.getName());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int hashCode = 17;

//        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        hashCode = this.getName().hashCode();
        return hashCode;
    }
}
