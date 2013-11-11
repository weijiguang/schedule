/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.application;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author Weir
 */
public class ApplicationContextHandler implements ApplicationContextAware {

    private static ApplicationContext appCtx;

//    加载Spring配置文件时，如果Spring配置文件中所定义的Bean类，如果该类实现了ApplicationContextAware接口，
//    那么在加载Spring配置文件时，会自动调用ApplicationContextAware接口中的
//    public void setApplicationContext(ApplicationContext context) throws BeansException
//    方法，并且自动可获得ApplicationContext 对象。前提必须在Spring配置文件中指定改类。
    /**
     * 此方法可以把ApplicationContext对象inject到当前类中作为一个静态成员变量。
     *
     * @param ac ApplicationContext 对象.
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
//        throw new UnsupportedOperationException("Not supported yet.");
        //获得该ApplicationContext引用
        appCtx = ac;
    }

    /**
     * 这是一个便利的方法，帮助我们快速得到一个BEAN
     *
     * @param beanName bean的名字
     * @return 返回一个bean对象
     */
    public static Object getBean(String beanName) {
        return appCtx.getBean(beanName);
    }
}
