/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytest;

/**
 *
 * @author Weir
 */
public interface NodeModel<T> {

    Long getId();

    T getParent();

    void setId(Long id);

    void setParent(T parent);

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    @Override
    String toString();
}
