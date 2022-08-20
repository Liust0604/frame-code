package com.mori.dao;

import com.mori.domain.Account;

import java.util.List;

public interface AccountDao {
    void saveAccount();

    List<Account> findAllAccount();

    Account findAccountById(Integer accountId);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer accountId);
}
