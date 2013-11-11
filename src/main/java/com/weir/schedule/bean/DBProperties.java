/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.bean;

import com.weir.schedule.application.Constant;
import com.weir.schedule.application.PropertiesUnit;
import com.weir.schedule.bean.unit.Msg;
import com.weir.schedule.model.Property;
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
@Named(value = "dbProperties")
@ViewScoped
public class DBProperties implements Serializable {
    
    private static Logger log = Logger.getLogger(DBProperties.class);
    private static final long serialVersionUID = 1431456546742L;    
//    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    @Inject
    private PropertiesUnit pu;
    private List<Property> propertyList;
    
    public DBProperties() {
    }
    
    private void loadFile() {
        if (!pu.getProperties().isEmpty()) {
            try {
                pu.getProperties().refresh();
            } catch (ConfigurationException ex) {
                log.error("reload Properties file error.");
            }
        } else {
            pu.loadProperties(Constant.DB_CONNECTION_PROPERTIES_FILE_TEST);
        }
    }
    
    public String list() {
        loadFile();
        propertyList = pu.getPropertiesList();
        return "/pages/sys/dbconfiglist";
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
    
    public void onEdit(RowEditEvent event) {
        Msg.info(((Property) event.getObject()).getKey());
    }
    
    public void onCancel(RowEditEvent event) {
        Msg.error(((Property) event.getObject()).getValue());
    }
    
    public List<Property> getPropertyList() {
        return propertyList;
    }
}