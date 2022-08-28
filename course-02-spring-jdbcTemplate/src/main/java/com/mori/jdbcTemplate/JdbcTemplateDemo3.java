package com.mori.jdbcTemplate;

import com.mori.mapper.AccountDao;
import com.mori.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * mapper 操作
 */
public class JdbcTemplateDemo3 {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountDao accountDao = (AccountDao) ac.getBean("accountDao");

        Account account = accountDao.findAccountById(12);
        System.out.println(account);
    }
}
