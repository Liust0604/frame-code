package com.mori.service;

import com.mori.domain.Account;

public interface AccountService {
    Account findAccountById(Integer accoutId);

    /**
     * 转账
     *
     * @param sourceName
     * @param targetName
     * @param balance
     */
    void transfer(String sourceName, String targetName, Float balance);
}
