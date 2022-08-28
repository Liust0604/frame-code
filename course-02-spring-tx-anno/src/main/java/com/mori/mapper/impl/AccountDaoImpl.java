package com.mori.mapper.impl;

import com.mori.mapper.AccountDao;
import com.mori.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiuShitian
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account findAccountById(Integer accoutId) {
        List<Account> accounts = jdbcTemplate.query("select * from db2.account where id = ?", new BeanPropertyRowMapper<>(Account.class), accoutId);
        if (accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0);
    }

    @Override
    public Account findAccountByName(String accoutName) {
        List<Account> accounts = jdbcTemplate.query("select * from db2.account where name = ?", new BeanPropertyRowMapper<>(Account.class), accoutName);
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
        int count = jdbcTemplate.update("update db2.account set name=? , balance=? where id=?", accout.getName(), accout.getBalance(), accout.getId());
    }
}
