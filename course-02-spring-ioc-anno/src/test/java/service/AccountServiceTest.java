package service;

import com.mori.config.SpringConfiguration;
import com.mori.domain.Account;
import com.mori.service.AccountService;
import com.mori.service.impl.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class) //指定注解方式、配置类
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

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
        account.setBalance(500f);
        account.setName("xixi");
        accountService.saveAccount(account);
    }

    @Test
    public void testUpdateAccount() {
        Account account = new Account();
        account.setId(7);
        account.setBalance(600f);
        account.setName("xiaoxi"); // 不填的话为null,因为没有动态加载查询条件
        accountService.updateAccount(account);
    }

    @Test
    public void testDeleteAccount() {
        accountService.deleteAccount(7);
    }
}
