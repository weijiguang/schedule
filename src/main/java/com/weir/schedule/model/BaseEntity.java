/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 *
 * @author Weir
 */
//@MappedSuperclass定义该类作为其他实体类的父类 
//如果在使用@MappedSuperclass标签的地方使用了@Entity标签的话，
//会让JPA错误的认为所有的Entity都是在一张数据表中的。
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -5554308939380869754L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.domain.Persistable#getId()
     */
    public Long getId() {

        return id;
    }

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    protected void setId(final Long id) {

        this.id = id;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.domain.Persistable#isNew()
     */
    public boolean isNew() {

        return null == getId();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
//        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
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

        BaseEntity that = (BaseEntity) obj;

        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }
}
