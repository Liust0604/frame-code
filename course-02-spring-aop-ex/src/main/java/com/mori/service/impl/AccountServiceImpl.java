package com.mori.service.impl;


import com.mori.dao.AccountDao;
import com.mori.domain.Account;
import com.mori.service.AccountService;
import com.mori.utils.TransactionManager;

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

    private TransactionManager txManager;

    //set方式注入TransactionManager
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    @Override
    public List<Account> findAllAccount() {
        try {
            //1、开启事务
            txManager.begin();
            //2、jdbc操作
            List<Account> accounts = accountDao.findAllAccount();
            //3、提交事务
            txManager.commit();
            //4、返回结果
            return accounts;
        } catch (Exception e) {
            //回滚
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //释放连接
            txManager.release();
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            txManager.begin();
            Account accounts = accountDao.findAccountById(accountId);
            txManager.commit();
            return accounts;
        } catch (Exception e) {
            txManager.rollback();
            throw new RuntimeException(e);
        } finally {
            txManager.release();
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            txManager.begin();
            accountDao.saveAccount(account);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
        } finally {
            txManager.release();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            txManager.begin();
            accountDao.updateAccount(account);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
        } finally {
            txManager.release();
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        try {
            txManager.begin();
            accountDao.deleteAccount(accountId);
            txManager.commit();
        } catch (Exception e) {
            txManager.rollback();
        } finally {
            txManager.release();
        }
    }

    //转账
    @Override
    public void transfer(String sourceName, String targetName, Float balance) {
        //问题：原来是一个操作建立一个连接，若中途异常，前面执行完成，后面的不再执行，不符合事务的一致性
        //解决：需要使用ThreadLocal对象，Connection和当前线程绑定，使一个线程中只有一个能控制事务的对象
        try {
            //1、开启事务
            txManager.begin();
            //2、jdbc操作
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
            int i = 1 / 0;
            //6、转入账户 - 更新
            accountDao.updateAccount(target);
            //3、提交事务
            txManager.commit();
            //4、返回结果
        } catch (Exception e) {
            //回滚
            txManager.rollback();
            e.printStackTrace();
        } finally {
            //释放连接
            txManager.release();
        }
    }
}
