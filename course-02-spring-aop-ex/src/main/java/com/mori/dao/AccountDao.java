package com.mori.dao;

import com.mori.domain.Account;

import java.util.List;

public interface AccountDao {

    List<Account> findAllAccount();

    Account findAccountById(Integer accountId);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer accountId);

    /**
     * 根据名称查询账户
     *
     * @param accountName
     * @return 唯一一个结果返回，没有结果返回null，结果集超过一个抛异常
     */
    Account findAccountByName(String accountName);
}
