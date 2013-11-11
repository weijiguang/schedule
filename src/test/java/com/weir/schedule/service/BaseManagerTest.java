/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.service;

import com.weir.schedule.dao.security.AccountDao;
import com.weir.schedule.model.security.Account;
import java.util.Iterator;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Weir
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dao-context.xml"})
@Transactional
@ActiveProfiles(profiles = "development")
public class BaseManagerTest {

    private static Logger log = Logger.getLogger(BaseManagerTest.class);
    @Inject
    AccountDao dao;
    Account e;

    public BaseManagerTest() {
    }

    @Test
    public void testSave() {
        e = new Account();
        e.setName("yaha");
        e.setPassword("*******");
        e.setDescription("testinggggggggggggggggggggggg");

        dao.save(e);
        Long id = new Long(0);
        Iterator it = dao.findAll().iterator();

        while (it.hasNext()) {
            id = ((Account) it.next()).getId();
            System.out.println("********** id = " + id + " **********");
        }

        System.out.println(dao.findOne(id).toString());
    }

    @Test
    public void testFind() {
    }
}
