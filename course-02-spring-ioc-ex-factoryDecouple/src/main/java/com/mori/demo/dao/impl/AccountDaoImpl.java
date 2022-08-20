package com.mori.demo.dao.impl;

import com.mori.demo.dao.AccountDao;

/**
 * 模拟持久层
 */
public class AccountDaoImpl implements AccountDao {
    @Override
    public void saveAccount() {
        System.err.println("保存账户！");
    }
}
