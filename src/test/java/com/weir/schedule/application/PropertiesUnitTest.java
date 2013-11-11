/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.application;

import com.weir.schedule.model.Property;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Weir
 */
public class PropertiesUnitTest {
    
    private static Logger log = Logger.getLogger(PropertiesUnitTest.class);
    
    public PropertiesUnitTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
//    @Test
    public void testLoadProperties() {
        PropertiesUnit pu = new PropertiesUnit();
        pu.loadProperties("db_test.properties");
        Iterator<Property> it = pu.getPropertiesList().iterator();
        while (it.hasNext()) {
            Property p = it.next();
            log.debug(p.getKey() + "=" + p.getValue());
        }
//        String path = PropertiesUnit.class.getClassLoader().getResource("").getPath();
//        pu.setValue("target.jdbc.password", "test33333333333333333");
//        try {
//            pu.saveFile(path + "/db_test", "");
//        } catch (FileNotFoundException ex) {
//          log.debug(ex);
//        } catch (IOException ex) {
//           log.debug(ex);
//        }
        
//        log.debug("path===="+path);
    }
}
