package com.mori.service;

import com.mori.domain.Account;

import java.util.List;

public interface AccountService {
    void saveAccount();

    List<Account> findAllAccount();

    Account findAccountById(Integer accountId);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer accountId);
}
