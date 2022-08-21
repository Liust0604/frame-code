package com.mori.dao.impl;

import com.mori.dao.AccountDao;
import com.mori.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * @author LiuShitian
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

    @Override
    public Account findAccountById(Integer accoutId) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from db2.account where id = ?", new BeanPropertyRowMapper<>(Account.class), accoutId);
        if (accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0);
    }

    @Override
    public Account findAccountByName(String accoutName) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from db2.account where name = ?", new BeanPropertyRowMapper<>(Account.class), accoutName);
        if (accounts.isEmpty()) {
            return null;
        }
        if (accounts.size() > 1) {
            throw new RuntimeException("结果集不唯一.");
        }
        return accounts.get(0);
    }

    @Override
    public void updateAccount(Account accout) {
        int count = super.getJdbcTemplate().update("update db2.account set name=? , balance=? where id=?", accout.getName(), accout.getBalance(), accout.getId());
    }
}
