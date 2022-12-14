package com.mori.mapper.impl;


import com.mori.mapper.AccountDao;
import com.mori.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * 模拟持久层
 */
public class AccountDaoImpl implements AccountDao {

    @Override
    public void saveAccount() {
        System.err.println("保存账户！");
    }

    /*案例*/

    private QueryRunner runner;

    //set方式注入
    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    @Override
    public List<Account> findAllAccount() {
        try {
            return runner.query("select * from db2.account", new BeanListHandler<Account>(Account.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            return runner.query("select * from db2.account where id = ?", new BeanHandler<Account>(Account.class), accountId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update("insert into db2.account(name,balance) values (?,?)", account.getName(), account.getBalance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update("update db2.account set name=?, balance=? where id=?", account.getName(), account.getBalance(), account.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        try {
            runner.update("delete from db2.account where id = ?", accountId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
