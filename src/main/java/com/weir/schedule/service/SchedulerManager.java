/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.service;

import com.weir.schedule.model.JobData;
import com.weir.schedule.model.ParamKey;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Map;
import java.util.Set;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

/**
 *
 * @author Weir
 */
@Named
public class SchedulerManager {

    private static Logger log = Logger.getLogger(SchedulerManager.class);
    private static SchedulerFactory sf = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "WEIR_JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "WEIR_TRIGGERGROUP_NAME";

    /**
     * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     *
     * @param jobName 任务名
     * @param jobClass 任务执行类
     * @param cronExpression 时间设置，参考quartz说明文档
     * @throws SchedulerException
     */
    public void addJob(String jobName, Class jobClass, String description,
            CronExpression cronExpression, Map<String, String> params)
            throws ParseException, SchedulerException {
        try {
            if (jobName == null || jobClass == null || jobName.trim().length() == 0) {
                throw new SchedulerException("9000088");
            }
            if (null == description) {
                description = "";
            }
            Scheduler sched = sf.getScheduler();
            // 任务名，任务组，任务执行类.usingJobData，使用JobDataMap传递参数。
            JobKey jobkey = new JobKey(jobName, JOB_GROUP_NAME);
            if (sched.checkExists(jobkey)) {
                throw new SchedulerException("already exists job " + jobkey.getName());
            }
            JobDetail job = newJob(jobClass)
                    .withIdentity(jobkey)
                    .withDescription(description)
                    .usingJobData(new JobDataMap(params))
                    .build();

            // 触发器。withSchedule设定触发器时间规则。
            TriggerKey tk = new TriggerKey(jobName, TRIGGER_GROUP_NAME);
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
        } catch (SchedulerException ex) {
            log.error(ex);
            throw ex;
        }
    }

    /**
     * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName
     */
    public void removeJob(String jobName) throws SchedulerException {
        Scheduler sched = sf.getScheduler();
        TriggerKey tk = new TriggerKey(jobName, TRIGGER_GROUP_NAME);
        if (sched.checkExists(tk)) {
            // 停止触发器
            sched.pauseTrigger(tk);
            // 移除触发器
            sched.unscheduleJob(tk);
        }
        JobKey jobkey = new JobKey(jobName, JOB_GROUP_NAME);
        if (sched.checkExists(jobkey)) {
            // 删除任务
            sched.deleteJob(jobkey);
        }
    }

    /**
     * 检查一个任务是否存在(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName
     */
    public boolean checkExistsJob(String jobName) throws SchedulerException {
        try {
            Scheduler sched = sf.getScheduler();
            JobKey jobkey = new JobKey(jobName, JOB_GROUP_NAME);
            return sched.checkExists(jobkey);
        } catch (SchedulerException ex) {
            log.error(ex);
            throw ex;
        }
    }

    /**
     * 启动所有定时任务
     */
    public void start() throws SchedulerException {
        Scheduler sched = sf.getScheduler();
        try {
            if (!sched.isStarted()) {
                sched.start();
            }
        } catch (SchedulerException ex) {
            log.error(ex);
            throw ex;
        }
    }

    /**
     * 关闭所有定时任务
     */
    public void shutdown() throws SchedulerException {
        Scheduler sched = sf.getScheduler();
        try {
            if (!sched.isShutdown()) {
                sched.shutdown(true);
            }
        } catch (SchedulerException ex) {
            log.error(ex);
            throw ex;
        }
    }

    /**
     * 中止指定的定时任务
     */
    public void pauseJob(String jobName) throws SchedulerException {
        Scheduler sched = sf.getScheduler();
        JobKey jobkey = new JobKey(jobName, JOB_GROUP_NAME);
        TriggerKey tk = new TriggerKey(jobName, TRIGGER_GROUP_NAME);
        try {
            if (sched.checkExists(jobkey)) {
                if (sched.getTriggerState(tk) == TriggerState.NORMAL) {
                    sched.pauseJob(jobkey);
                }
            }
        } catch (SchedulerException ex) {
            log.error(ex);
            throw ex;
        }
    }

    /**
     * 重启指定的定时任务
     */
    public void resumeJob(String jobName) throws SchedulerException {
        Scheduler sched = sf.getScheduler();
        JobKey jobkey = new JobKey(jobName, JOB_GROUP_NAME);
        TriggerKey tk = new TriggerKey(jobName, TRIGGER_GROUP_NAME);
        try {
            if (sched.checkExists(jobkey)) {
                if (sched.getTriggerState(tk) == TriggerState.PAUSED) {
                    sched.resumeJob(jobkey);
                }
            }
        } catch (SchedulerException ex) {
            log.error(ex);
            throw ex;
        }
    }

    /**
     * 返回所有任务的信息
     */
    public List<JobData> getAllJob() throws SchedulerException {
        List<JobData> jlst = new ArrayList<JobData>();
        try {
            Scheduler sched = sf.getScheduler();
            JobDetailImpl job;
            JobKey jobkey;
            TriggerKey tk;
            CronTrigger trigger;
            JobData jd;
            Map<String, Object> params;
            GroupMatcher<JobKey> gm = GroupMatcher.groupEquals(JOB_GROUP_NAME);

            Set<JobKey> jobKeys = sched.getJobKeys(gm);
            for (Iterator<JobKey> it = jobKeys.iterator(); it.hasNext();) {
                jobkey = it.next();
                jd = new JobData();
                params = new HashMap<String, Object>();
                tk = new TriggerKey(jobkey.getName(), TRIGGER_GROUP_NAME);
                job = (JobDetailImpl) sched.getJobDetail(jobkey);
                trigger = (CronTrigger) sched.getTrigger(tk);
                jd.setName(job.getName());
                jd.setGroup(job.getGroup());
                jd.setDescription(job.getDescription());
                jd.setCronex(trigger.getCronExpression());
                switch (sched.getTriggerState(tk)) {
                    case BLOCKED:
                        jd.setState(JobData.State.BLOCKED);
                        break;
                    case COMPLETE:
                        jd.setState(JobData.State.COMPLETE);
                        break;
                    case ERROR:
                        jd.setState(JobData.State.ERROR);
                        break;
                    case NONE:
                        jd.setState(JobData.State.NONE);
                        break;
                    case NORMAL:
                        jd.setState(JobData.State.NORMAL);
                        break;
                    case PAUSED:
                        jd.setState(JobData.State.PAUSED);
                        break;
                }
                params = job.getJobDataMap();
                if (params.containsKey(ParamKey._conditionSQL)) {
                    jd.setConditionSQL(params.get(ParamKey._conditionSQL).toString());
                }
                if (params.containsKey(ParamKey._sourceSQL)) {
                    jd.setSourceSQL(params.get(ParamKey._sourceSQL).toString());
                }
                if (params.containsKey(ParamKey._targetSQL)) {
                    jd.setTargetSQL(params.get(ParamKey._targetSQL).toString());
                }
                jlst.add(jd);
            }
            start();
            return jlst;
        } catch (SchedulerException ex) {
            log.error(ex);
            throw ex;
        }
    }

    public static void startByLoaded() throws SchedulerException {
        try {
            Scheduler sched = sf.getScheduler();
            if (!sched.isStarted()) {
                sched.start();
            }
        } catch (SchedulerException ex) {
            log.error("初始化加载异常：" + ex);
            throw ex;
        }
    }
}
