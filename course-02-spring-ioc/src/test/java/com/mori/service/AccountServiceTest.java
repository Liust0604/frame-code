package com.mori.service;

import com.mori.domain.Account;
import com.mori.service.impl.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml") //指定注解方式、配置类
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    /*@Before
    public void init() {
        //1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2、获取业务层对象
        accountService = ac.getBean("accountService", AccountServiceImpl.class);
    }*/


    @Test
    public void testFindAllAccount() {
        List<Account> allAccount = accountService.findAllAccount();
        System.out.println(allAccount);
    }

    @Test
    public void testFindAccountById() {
        Account account = accountService.findAccountById(2);
        System.out.println(account);
    }

    @Test
    public void testSaveAccount() {
        Account account = new Account();
        account.setMoney(500f);
        account.setName("xixi");
        accountService.saveAccount(account);
    }

    @Test
    public void testUpdateAccount() {
        Account account = new Account();
        account.setId(7);
        account.setMoney(600f);
        account.setName("xiaoxi"); // 不填的话为null,因为没有动态加载查询条件
        accountService.updateAccount(account);
    }

    @Test
    public void testDeleteAccount() {
        accountService.deleteAccount(7);
    }
}
