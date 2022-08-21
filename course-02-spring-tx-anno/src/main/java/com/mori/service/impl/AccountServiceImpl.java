package com.mori.service.impl;

import com.mori.dao.AccountDao;
import com.mori.domain.Account;
import com.mori.service.AccountService;

/**
 * @author LiuShitian
 */
public class AccountServiceImpl implements AccountService {

    AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    //转账
    @Override
    public void transfer(String sourceName, String targetName, Float balance) {
        System.out.println("转账开始…");
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
        System.out.println("转账结束…");
    }
}
