/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model;

/**
 *
 * @author Weir
 */
public class ParamKey {
    public static final String _conditionSQL = "conditionSQL";
    public static final String _sourceSQL = "sourceSQL";
    public static final String _targetSQL = "targetSQL";
    public static final String _isReverse = "isReverse";
    
    private String conditionSQL;
    private String sourceSQL;
    private String targetSQL;
    private String isReverse;

    public ParamKey() {
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

    public String getIsReverse() {
        return isReverse;
    }

    public void setIsReverse(String isReverse) {
        this.isReverse = isReverse;
    }
}
