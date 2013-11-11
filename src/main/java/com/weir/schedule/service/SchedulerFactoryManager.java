/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.service;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Weir
 */
public class SchedulerFactoryManager implements Serializable {

    private static Logger log = LoggerFactory.getLogger(SchedulerFactoryManager.class);
    private static final long serialVersionUID = 143456456546742L;
    private static SchedulerFactory sf = new StdSchedulerFactory();
    ;
    private String jobGroup = "DEFAULT_JOB_GROUP";
    private String triggerGroup = "DEFAULT_TRIGGER_GROUP";

    /**
     * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     *
     * @param schedName 计划名
     * @param jobClass 任务执行类
     * @param cronExpression 时间设置，参考quartz说明文档
     * @throws SchedulerException
     */
    public void addSchedule(String schedName, Class jobClass, String description,
            CronExpression cronExpression, Map<String, String> params) throws SchedulerException {
        Collection< Scheduler> cs = sf.getAllSchedulers();
        if (cs.contains(sf.getScheduler(schedName))) {
            log.error("指定的计划已存在");
            throw new SchedulerException();
        }
        Scheduler sched = sf.getScheduler(schedName);
        JobKey jobkey = new JobKey(schedName, jobGroup);

        JobDetail job = newJob(jobClass)
                .withIdentity(jobkey)
                .withDescription(description)
                .usingJobData(new JobDataMap(params))
                .build();
        // 触发器。withSchedule设定触发器时间规则。
        TriggerKey tk = new TriggerKey(schedName, triggerGroup);
        CronTrigger trigger = newTrigger()
                .withIdentity(tk)
                .withSchedule(cronSchedule(cronExpression))
                .build();
        //设定调度器的任务和触发器
        sched.scheduleJob(job, trigger);
        // 启动 
        if (!sched.isStarted()) {
            sched.start();
        }
    }
}
