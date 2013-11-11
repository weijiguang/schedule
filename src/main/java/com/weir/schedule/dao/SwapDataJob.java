/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.dao;

import com.weir.schedule.application.ApplicationContextHandler;
import com.weir.schedule.bean.JobBean;
import com.weir.schedule.model.ParamKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Weir
 */
public final class SwapDataJob implements Job {

    private static Logger log = Logger.getLogger(JobBean.class);
    private static final long serialVersionUID = 14345654642742L;
    private JdbcTemplate sourceJT;
    private JdbcTemplate targetJT;
//    限制条件语句
    private String conditionSQL = "";
//    源数据查询语句
    private String sourceSQL = "";
//    目标数据操作语句
    private String targetSQL = "";
//    是否反向标志
    private boolean isReverse = false;

    public SwapDataJob() {
        DataSource sourceDS = (DataSource) ApplicationContextHandler.getBean("sourceDS");
        DataSource targetDS = (DataSource) ApplicationContextHandler.getBean("targetDS");
        setSourceJdbcTemplate(sourceDS);
        setTargetJdbcTemplate(targetDS);
    }

//    @Resource(name = "sourceDS")
    public void setSourceJdbcTemplate(DataSource dataSource) {
        sourceJT = new JdbcTemplate(dataSource);
    }

//    @Resource(name = "targetDS")
    public void setTargetJdbcTemplate(DataSource dataSource) {
        targetJT = new JdbcTemplate(dataSource);
    }

    /**
     * ==============quartz—向job动态传递参数=================== 首先在运行前传入参数：
     * JobDetail类的getJobDataMap()方法，返回值JobDataMap，可以在此基础上进行Map操作。 例如：
     * job.getJobDataMap().put("love", "I love you very much!"); 其次在job中获得参数：
     * 在execute方法中，传入上下文context，如下所示： public void execute(JobExecutionContext
     * jec) 再获得JobDataMap，从Map中获得所需数据，示例代码如下： String jobName =
     * jec.getJobDetail().getName(); JobDataMap dataMap =
     * jec.getJobDetail().getJobDataMap(); String strData =
     * dataMap.getString("love");
     */
    @Transactional
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {

//        log.debug("=============begin execute job==================");

        JobDataMap data = jec.getJobDetail().getJobDataMap();
        if (!data.containsKey(ParamKey._conditionSQL)) {
            log.error("缺少条件限制语句。");
            throw new JobExecutionException();
        }
        if (!data.containsKey(ParamKey._sourceSQL)) {
            log.error("缺少源数据库查询语句。");
            throw new JobExecutionException();
        }
        if (!data.containsKey(ParamKey._targetSQL)) {
            log.error("缺少目标数据库操作语句。");
            throw new JobExecutionException();
        }
        if (data.containsKey(ParamKey._isReverse)) {
            isReverse = data.getBooleanValue("isReverse");
        }
        conditionSQL = data.getString("conditionSQL").toUpperCase().trim();
        sourceSQL = data.getString("sourceSQL").toUpperCase().trim();
        targetSQL = data.getString("targetSQL").toUpperCase().trim();
        //执行数据交换
        List<Map<String, Object>> srcList = queryForListSourceDB(sourceSQL,
                queryConditionMapFromTargetDB(conditionSQL));
        String[] tqs = targetSQL.split(";;");
        for (String tq : tqs) {
            updateTargetDB(tq, srcList);
        }
//        updateTargetDB(targetSQL,
//                queryForListSourceDB(sourceSQL,
//                queryConditionMapFromTargetDB(conditionSQL)));

//        log.debug("=============end execute job==================");
    }

    private Map<String, Object> queryConditionMapFromTargetDB(String conditionSQL) {
        return !isReverse
                ? targetJT.queryForMap(conditionSQL)
                : sourceJT.queryForMap(conditionSQL);
    }

    private List<Map<String, Object>> queryForListSourceDB(String sourceSQL,
            Map<String, Object> conditionParamsMap) {
        //命名参数解析成参数占位符(即将命名参数解析成“？”)。
        String sql = NamedParameterUtils.parseSqlStatementIntoString(sourceSQL);
        //从Map（key值必须也命名参数的名称一致）中获取参数值，顺序也命名参数一致。
        Object[] args = NamedParameterUtils.buildValueArray(sourceSQL, conditionParamsMap);
//        log.debug("conditionParamsMap:" + conditionParamsMap);
        return !isReverse
                ? sourceJT.queryForList(sql, args)
                : targetJT.queryForList(sql, args);
    }

//    @Transactional
    private int[] updateTargetDB(String targetSQL,
            List<Map<String, Object>> sourceDatas) {
        //命名参数解析成参数占位符(即将命名参数解析成“？”)。
        String sql = NamedParameterUtils.parseSqlStatementIntoString(targetSQL);
        List<Object[]> batchArgs = new ArrayList<Object[]>();

        for (Map<String, Object> paramMap : sourceDatas) {
            //从Map（key值必须也命名参数的名称一致）中获取参数值，顺序也命名参数一致。
            Object[] args = NamedParameterUtils.buildValueArray(targetSQL, paramMap);
            batchArgs.add(args);
        }

//        log.debug("sql::" + sql);        
//        log.debug("batchArgs size:" + batchArgs.size());
//        Iterator<Object[]> it = batchArgs.iterator();
//        while (it.hasNext()) {
//            for (Object o : it.next()) {
//                log.debug("param value:" + o);
//            }
//        }
//        
        return !isReverse
                ? targetJT.batchUpdate(sql, batchArgs)
                : sourceJT.batchUpdate(sql, batchArgs);
    }
}
