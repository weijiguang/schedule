/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytest;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author Weir
 */
public class NodeModelImpl implements NodeModel<NodeModelImpl> {

    private Long id;
    private NodeModelImpl parent;

    public NodeModelImpl() {
    }

    public NodeModelImpl(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public NodeModelImpl getParent() {
        return parent;
    }

    @Override
    public void setParent(NodeModelImpl parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {

        if (null == obj && null == this.getId()) {
            return true;
//            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        NodeModelImpl that = (NodeModelImpl) obj;
        if (null == this.getId() && null == that.getId()) {
            return true;
        }
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
