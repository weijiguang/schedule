/* 
 * Copyright 2005 - 2009 Terracotta, Inc. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */
package com.weir.schedule.dao;

import com.weir.schedule.model.ParamKey;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;

/**
 * <p> This is just a simple job that gets fired off many times by example 1
 * </p>
 *
 * @author Bill Kratzer
 */
public class SimpleJob implements Job {

    private static Logger log = Logger.getLogger(SimpleJob.class);
    private static final long serialVersionUID = 143451654642742L;
    //    
//    @Resource(name = "sourceJdbcTemplate")
    private JdbcTemplate sourceJT;
//    @Resource(name = "targetJdbcTemplate")
    private JdbcTemplate targetJT;
//    限制条件语句
    private String conditionSQL = "";
//    源数据查询语句
    private String sourceSQL = "";
//    目标数据操作语句
    private String targetSQL = "";
//    是否反向标志
    private boolean isReverse = false;

    /**
     * Quartz requires a public empty constructor so that the scheduler can
     * instantiate the class whenever it needs.
     */
    public SimpleJob() {
    }

    /**
     * <p> Called by the
     * <code>{@link org.quartz.Scheduler}</code> when a
     * <code>{@link org.quartz.Trigger}</code> fires that is associated with the
     * <code>Job</code>. </p>
     *
     * @throws JobExecutionException if there is an exception while executing
     * the job.
     */
    @Override
    public void execute(JobExecutionContext jec)
            throws JobExecutionException {
        JobDataMap jdm = jec.getJobDetail().getJobDataMap();

//        for (Entry<String, Object> et : jdm.entrySet()) {
//            _log.debug("key:" + et.getKey() + "\tvalue:" + et.getValue());
//        }

        if (!jdm.containsKey(ParamKey._conditionSQL)) {
            return;
        }
        if (!jdm.containsKey(ParamKey._sourceSQL)) {
            return;
        }
        if (!jdm.containsKey(ParamKey._targetSQL)) {
            return;
        }
        if (jdm.containsKey(ParamKey._isReverse)) {
            this.isReverse = Boolean.valueOf(jdm.getString(ParamKey._isReverse));
        }

        sourceSQL = jdm.getString(ParamKey._sourceSQL);
        Map<String, String> p = new HashMap<String, String>();
//        p.put("name", "my name");
        p.put("name", "my name");
//        命名参数解析成参数占位符
        String sql = NamedParameterUtils.parseSqlStatementIntoString(sourceSQL);
//        从Map中获取参数值，顺序也命名参数一致。
        Object[] args = NamedParameterUtils.buildValueArray(sourceSQL, p);

        log.debug("++++++++++++++++++++++++++++++++++++++++++++++++");
        log.debug(sql);
        for (int i = 0; i < args.length; i++) {
            log.debug(args[i].toString());
        }
        log.debug("================================================");
    }
//    private Map<String, Object> getConditionMap(String conditionSQL) {
//        return !isReverse
//                ? targetJT.queryForMap(conditionSQL)
//                : sourceJT.queryForMap(conditionSQL);
//    }
//
//    private List<Map<String, Object>> getSourceDatas(String sourceSQL,
//            Map<String, Object> conditionParamsMap) {
//        log.debug("conditionParamsMap values:" + conditionParamsMap);
//
//        String sql = NamedParameterUtils.parseSqlStatementIntoString(sourceSQL);
//        Object[] args = NamedParameterUtils.buildValueArray(sql, conditionParamsMap);
//
//        return !isReverse
//                ? sourceJT.queryForList(sql, args)
//                : targetJT.queryForList(sql, args);
//    }
//
//    private int[] toTargetDatas(String targetSQL,
//            List<Map<String, Object>> sourceDatas) {
//
//        log.debug("sourceDatas values:" + sourceDatas);
//
//        Object[] args;
//        String[] sqls = new String[sourceDatas.size()];
//
//        Iterator<Map<String, Object>> it = sourceDatas.iterator();
//        for (int i = 0; it.hasNext(); i++) {
//            targetSQL = NamedParameterUtils.parseSqlStatementIntoString(targetSQL);
//            args = NamedParameterUtils.buildValueArray(targetSQL, it.next());
//            sqls[i] = targetSQL;
//        }
//
//        return !isReverse
//                ? targetJT.batchUpdate(sqls)
//                : sourceJT.batchUpdate(sqls);
//    }
}
