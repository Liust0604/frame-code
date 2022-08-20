package com.mori.demo.service.impl;

import com.mori.demo.dao.AccountDao;
import com.mori.demo.factory.BeanFactory;
import com.mori.demo.service.AccountService;

/**
 * 模拟业务层
 */
public class AccountServiceImpl implements AccountService {

    // AccountDao accountDao = new AccountDaoImpl();
    AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");

    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
