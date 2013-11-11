/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Weir
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-webapp-context.xml"})
//@Transactional
public class SchedulerManagerTest {

    private static Logger log = Logger.getLogger(SchedulerManagerTest.class);
//    private static final long serialVersionUID = 14546742L;
//    @Inject
//    private SchedulerManager manager;
    private JdbcTemplate sourceJT;
    private JdbcTemplate targetJT;

    @Resource(name = "sourceDS")
    public void setSourceJdbcTemplate(DataSource dataSource) {
        sourceJT = new JdbcTemplate(dataSource);
    }

    @Resource(name = "targetDS")
    public void setTargetJdbcTemplate(DataSource dataSource) {
        targetJT = new JdbcTemplate(dataSource);
    }

    public SchedulerManagerTest() {
    }

    @Test
    public void testGetAllJob() {
//        log.debug("begin testing");
//        try {
//            for (JobData job : manager.getAllJob()) {
//                log.debug(job.getName() + " " + job.getCronex());
//            }
//            //        Thread.sleep(20*1000L);
//        } catch (SchedulerException ex) {
//            log.debug("异常信息：" + ex);
//        }
        Assert.assertTrue(true);
    }

//    @Test
    public void testAddJob() {
    }

//    @Test
    public void testRemoveJob() {
    }

//    @Test
    public void testStart() {
    }

//    @Test
    public void testShutdown() {
    }

//    @Test
    public void testPauseJob() {
    }

//    @Test
    public void testResumeJob() {
    }

//    @Test
    public void testJTA() {
        String oq = "SELECT * FROM zztest";
        String oq2 = "SELECT 1 FROM dual";
        String mq = "SELECT 1";
        String mq2 = "select blh,zycs,zxks,ksbh from aa";
        List<Map<String, Object>> list;
        list = sourceJT.queryForList(mq);
        for (Iterator<Map<String, Object>> it = list.iterator(); it.hasNext();) {
            log.debug("======mssql查询的数据：  " + it.next() + "   ==============");
        }
        list = this.targetJT.queryForList(oq2);
        for (Iterator<Map<String, Object>> it = list.iterator(); it.hasNext();) {
            log.debug("======oracle查询的数据：  " + it.next() + "   ==============");
        }

//        sourceJT.queryForMap("");

        Assert.assertTrue(true);
    }
//    @Test
//    @Transactional
//    @Rollback(false)

    public void testJTAInsert() {
//        String oq = "insert into zztest(ID,CONTENT) values(9,'oracle的jta插入测试。333')";
//        String mq = "INSERT INTO [test3]([id],[txt])VALUES(2,'3333333')";
//        String mq2 = "select blh,zycs,zxks,ksbh from aa";
//        targetJT.update(oq);
//        this.sourceJT.update(mq);
        Assert.assertTrue(true);
    }
}
