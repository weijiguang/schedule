/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytest;

/**
 *
 * @author Weir
 */
public class TreeElement<T>{

    private T data;
    private String type;

    public TreeElement() {
        super();
    }

    public TreeElement(boolean leaf, T data) {
//        super(leaf);
        this.data = data;
        this.type = leaf ? "leaf" : "branch";
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   
    public String toString() {
        return this.data.toString();
    }
}
