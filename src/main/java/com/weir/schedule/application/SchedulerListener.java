/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.application;

import com.weir.schedule.service.SchedulerManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Weir
 */
public class SchedulerListener implements ServletContextListener {

    private static Logger log = LoggerFactory.getLogger(SchedulerListener.class);

    //服务启动时执行的事件
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            SchedulerManager.startByLoaded();
        } catch (SchedulerException ex) {
            log.error("服务器启动时，计划任务启动失败。" + ex);
        }
    }

    //服务停止时执行的事件
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
