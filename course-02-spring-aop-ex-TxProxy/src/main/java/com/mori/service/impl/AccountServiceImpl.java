package com.mori.service.impl;


import com.mori.mapper.AccountDao;
import com.mori.domain.Account;
import com.mori.service.AccountService;

import java.util.List;

/**
 * 模拟业务层
 * 【事务控制】都应该在业务层
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    //set方式注入AccountDao
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }

    //转账
    @Override
    public void transfer(String sourceName, String targetName, Float balance) {
        //问题：原来是一个操作建立一个连接，若中途异常，前面执行完成，后面的不再执行，不符合事务的一致性
        //解决：需要使用ThreadLocal对象，Connection和当前线程绑定，使一个线程中只有一个能控制事务的对象
        //1、转出名称 - 查询 - 转出账户
        Account source = accountDao.findAccountByName(sourceName);
        //2、转入名称 - 查询 - 转入账户
        Account target = accountDao.findAccountByName(targetName);
        //3、转出账户 - 减钱
        source.setBalance(source.getBalance() - balance);
        //4、转入账户 - 加钱
        target.setBalance(target.getBalance() + balance);
        //5、转出账户 - 更新
        accountDao.updateAccount(source);
        //int i = 1 / 0;
        //6、转入账户 - 更新
        accountDao.updateAccount(target);
    }
}
