/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.application;

import com.weir.schedule.model.Property;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Named;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

/**
 *
 * @author Weir
 */
@Named
public final class PropertiesUnit {

    private static Logger log = Logger.getLogger(PropertiesUnit.class);
    private PropertiesConfiguration config = new PropertiesConfiguration();

    /**
     * 初始化Configuration类
     */
    public PropertiesUnit() {
    }

    /**
     * 初始化Configuration类
     */
    public PropertiesUnit(String fileName) {
        loadProperties(fileName);
    }

    /**
     * 读取的配置文件。
     *
     * @param fileName 文件名称
     */
    public void loadProperties(String fileName) {
        if (!fileName.endsWith(".properties")) {
            fileName = new StringBuilder(fileName).append(".properties").toString();
        }
        try {
            config = new PropertiesConfiguration(fileName);
        } catch (ConfigurationException ex) {
            log.error(ex);
        }
    }

    public PropertiesConfiguration getProperties() {
        return config;
    }

    /**
     * 重载函数，得到key的值
     *
     * @param key 取得其值的键
     * @return key的值
     */
    public String getValue(String key) {
        return config.getString(key);
    }// end getValue(...)  

    /**
     * 清除properties文件中所有的key和其值
     */
    public void clear() {
        config.clear();
    }// end clear(); 

    /**
     * 改变或添加一个key的值，当key存在于properties文件中时该key的值被value所代替， 当key不存在时，该key的值是value
     *
     * @param key 要存入的键
     * @param value 要存入的值
     */
    public void setValue(String key, String value) {
        config.setProperty(key, value);
    }// end setValue(...)  

    /**
     * 将更改后的文件数据存入文件中。
     */
    public void saveFile() throws ConfigurationException {
        config.save();
    }// end saveFile(...)  

    public List<Property> getPropertiesList() {
        List<Property> list = new ArrayList<Property>();
        if (!config.isEmpty()) {
            Property prop;
            for (Iterator<String> it = config.getKeys(); it.hasNext();) {
                String key = it.next();
                prop = new Property();
                prop.setKey(key);
                prop.setValue(config.getString(key));
                list.add(prop);
            }
        }
        return list;
    }// end
}
