package com.mori.mapper;

import com.mori.domain.Account;

import java.util.List;

public interface AccountMapper {
    /**
     * 查询所有账户信息
     *
     * @return
     */
    public List<Account> findAll();

    /**
     * 保存账户信息
     *
     * @param account
     */
    public void saveAccount(Account account);
}
