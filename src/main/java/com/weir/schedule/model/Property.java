/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model;

/**
 *
 * @author Weir
 */
public class Property {

    private String key;
    private String value;

    public Property() {
    }

    public Property(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Get the value of value
     *
     * @return the value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value of value
     *
     * @param value new value of value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Get the value of key
     *
     * @return the value of key
     */
    public String getKey() {
        return key;
    }

    /**
     * Set the value of key
     *
     * @param key new value of key
     */
    public void setKey(String key) {
        this.key = key;
    }
}
