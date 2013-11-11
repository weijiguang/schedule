/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weir.schedule.service.security;

import com.weir.schedule.dao.BaseDao;
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
public class AccountManagerTest {

    private static Logger log = Logger.getLogger(AccountManagerTest.class);
    @Inject
    AccountDao dao;
    @Inject
    BaseDao adao;
    Account e;

    public AccountManagerTest() {
    }

//    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testSave() {
        e = new Account();
        e.setName("admin");
        e.setPassword("123456");
        log.info("e init value:" + e.toString());
        adao.save(e);
//        dao.save(e);
//        System.out.println("===========================================================");
//        Iterator<Account> it = dao.findAll().iterator();
//
//        while (it.hasNext()) {
//            System.out.println(it.next().toString());
////            log.debug(it.next().toString());
////            log.info(it.next().toString());
//        }

//        dao.findAll().iterator();
//        Assert.assertTrue(true);
    }
}
