package com.mori.dao.impl;


import com.mori.dao.AccountDao;
import org.springframework.stereotype.Repository;

/**
 * 模拟持久层
 */
@Repository
public class AccountDaoImpl implements AccountDao {
    @Override
    public void saveAccount() {
        System.err.println("保存账户！");
    }
}
