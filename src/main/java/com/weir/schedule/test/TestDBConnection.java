/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.test;

import com.weir.schedule.bean.TaskDBConfig;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Weir
 */
@Named
public class TestDBConnection {

    private static Logger log = Logger.getLogger(TestDBConnection.class);
    private static final long serialVersionUID = 123145654642L;
    private JdbcTemplate sourceJT;
    private JdbcTemplate targetJT;

    public TestDBConnection() {
    }

    @Resource(name = "sourceDS")
    public void setSourceJdbcTemplate(DataSource dataSource) {
        sourceJT = new JdbcTemplate(dataSource);
    }

    @Resource(name = "targetDS")
    public void setTargetJdbcTemplate(DataSource dataSource) {
        targetJT = new JdbcTemplate(dataSource);
    }

    public boolean TestSQLServer(String testQuery) {
        if (sourceJT instanceof JdbcTemplate) {
            try{
                return sourceJT.queryForList(testQuery).size() > 0;  
            }catch(Exception ex){
                return false;
            }
        }
        return false;
    }

    public boolean TestOracle(String testQuery) {
        if (targetJT instanceof JdbcTemplate) {
            try{
                return targetJT.queryForList(testQuery).size() > 0;
            }catch(Exception ex){
                return false;
            }
        }
        return false;
    }
}
