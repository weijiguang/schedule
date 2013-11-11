/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.bean;

import com.weir.schedule.bean.unit.Msg;
import com.weir.schedule.model.JobData;
import com.weir.schedule.model.ParamKey;
import com.weir.schedule.service.SchedulerManager;
import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;

/**
 *
 * @author Weir
 */
@Named
@ViewScoped
public class JobBean implements Serializable {

    private static Logger log = Logger.getLogger(JobBean.class);
    private static final long serialVersionUID = 143456546742L;
    @Inject
    private SchedulerManager manager;
    private List<JobData> jobs;
    private JobData jd;
    private JobData selectedJob;

    public JobBean() {
//         com.mysql.jdbc.jdbc2.optional.MysqlXADataSource
//        oracle.jdbc.xa.client.OracleXADataSource
//        net.sourceforge.jtds.jdbcx.JtdsDataSource
    }

    public String prepareAdd() {
        this.jd = new JobData();
        return "add";
    }

    public String add() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        Map<String, String> params = new HashMap<String, String>();
        params.put(ParamKey._conditionSQL, jd.getConditionSQL());
        params.put(ParamKey._sourceSQL, jd.getSourceSQL());
        params.put(ParamKey._targetSQL, jd.getTargetSQL());

        try {
            if (manager.checkExistsJob(jd.getName())) {
                Msg.error("您指定的任务名已存在！请给定一个新的任务名。");
                return prepareAdd();
            }
            CronExpression cronExpression = new CronExpression(jd.getCronex());
            manager.addJob(jd.getName(), jd.getJobClass(), jd.getDescription(), cronExpression, params);
            Msg.info("操作成功");
            return list();
        } catch (ParseException ex) {
            Msg.error("触发时间配置错误。请重新配置！");
            return prepareAdd();
        } catch (Exception ex) {
            log.error(ex);
            Msg.error("操作失败");
            return null;
        }
    }

    public String list() {
        jobs = null;
        return "list";
    }

    public void remove() {
        try {
            if (null == selectedJob || "".equals(selectedJob.getName())) {
                Msg.error("请选择要删除的记录。");
                return;
            }
            manager.removeJob(selectedJob.getName());
            Msg.info("操作成功。");
        } catch (SchedulerException ex) {
            Msg.error("操作失败。");
            log.error(ex);
        }
    }

    /**
     * 中止指定的定时任务
     */
    public void pauseJob() {
        try {
            if (null == selectedJob || "".equals(selectedJob.getName())) {
                Msg.error("请选择要暂停的任务。");
                return;
            }
            manager.pauseJob(selectedJob.getName());
            Msg.info("操作成功。");
        } catch (SchedulerException ex) {
            Msg.error("操作失败。");
            log.error(ex);
        }
    }

    /**
     * 重启指定的定时任务
     */
    public void resumeJob() {
        try {
            if (null == selectedJob || "".equals(selectedJob.getName())) {
                Msg.error("请选择要重启的任务。");
                return;
            }
            manager.resumeJob(selectedJob.getName());
            Msg.info("操作成功。");
        } catch (SchedulerException ex) {
            Msg.error("操作失败。");
            log.error(ex);
        }
    }

    public List<JobData> getJobs() {
        try {
            jobs = manager.getAllJob();
        } catch (SchedulerException ex) {
            log.error(ex);
        }
        return jobs;
    }

    public JobData getJd() {
        return jd;
    }

    public void setJd(JobData jd) {
        this.jd = jd;
    }

    public JobData getSelectedJob() {
        return selectedJob;
    }

    public void setSelectedJob(JobData selectedJob) {
        this.selectedJob = selectedJob;
    }
}
