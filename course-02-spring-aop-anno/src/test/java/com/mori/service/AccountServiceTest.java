package com.mori.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LiuShitian
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    AccountService accountService;


    @Test
    public void testSaveAccount() {
        accountService.saveAccount();
    }

    @Test
    public void testUpdateAccount() {
        accountService.updateAccount(1);
    }

    @Test
    public void testDeleteAccount() {
        int i = accountService.deleteAccount(1);
    }
}
