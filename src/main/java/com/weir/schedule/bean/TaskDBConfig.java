/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.bean;

import com.weir.schedule.application.Constant;
import com.weir.schedule.application.PropertiesUnit;
import com.weir.schedule.bean.unit.Msg;
import com.weir.schedule.model.Property;
import com.weir.schedule.test.TestDBConnection;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Weir
 */
@Named
@ViewScoped
public class TaskDBConfig implements Serializable {
    
    private static Logger log = Logger.getLogger(TaskDBConfig.class);
    private static final long serialVersionUID = 1231456546742L;
    @Inject
    private PropertiesUnit pu;
    @Inject
    TestDBConnection testDBConn;
    private List<Property> propertyList;
    private String sourceDBconnStatus = "";
    private String targetDBconnStatus = "";
    
    public TaskDBConfig() {
    }
    
    private void loadFile() {
        if (!pu.getProperties().isEmpty()) {
            try {
                pu.getProperties().refresh();
            } catch (ConfigurationException ex) {
                log.error("reload Properties file error.");
            }
        } else {
            pu.loadProperties(Constant.DB_CONNECTION_PROPERTIES_FILE);
        }
    }
    
    public String list() {
        loadFile();
        propertyList = pu.getPropertiesList();
        return "/pages/sys/taskdbconfig";
    }
    
    public String save() {
        for (Property p : propertyList) {
            pu.setValue(p.getKey(), p.getValue());
        }
        try {
            pu.saveFile();
            Msg.info("保存成功。");
        } catch (ConfigurationException ex) {
            Msg.error("保存失败！");
        }
        return list();
    }
    
    public void testSQLServer() {
        String testQuery = pu.getValue("mssql.test.query");
        sourceDBconnStatus = testDBConn.TestSQLServer(testQuery)
                ? "成功" : "失败";
        log.debug("sourceDBconnStatus:" + sourceDBconnStatus);
    }
    
    public void testOracle() {
        String testQuery = pu.getValue("oracle.test.query");
        targetDBconnStatus = testDBConn.TestOracle(testQuery)
                ? "成功" : "失败";
        log.debug("targetDBconnStatus:" + targetDBconnStatus);
    }
    
    public void onEdit(RowEditEvent event) {
        Msg.info(((Property) event.getObject()).getKey());
    }
    
    public void onCancel(RowEditEvent event) {
        Msg.error(((Property) event.getObject()).getValue());
    }
    
    public List<Property> getPropertyList() {
        return propertyList;
    }
    
    public String getSourceDBconnStatus() {
        return sourceDBconnStatus;
    }
    
    public void setSourceDBconnStatus(String sourceDBconnStatus) {
        this.sourceDBconnStatus = sourceDBconnStatus;
    }
    
    public String getTargetDBconnStatus() {
        return targetDBconnStatus;
    }
    
    public void setTargetDBconnStatus(String targetDBconnStatus) {
        this.targetDBconnStatus = targetDBconnStatus;
    }
}
