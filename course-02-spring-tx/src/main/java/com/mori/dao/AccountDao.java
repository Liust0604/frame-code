package com.mori.dao;

import com.mori.domain.Account;

/**
 * @author LiuShitian
 */
public interface AccountDao {
    Account findAccountById(Integer accoutId);

    Account findAccountByName(String accoutName);

    void updateAccount(Account accout);
}
