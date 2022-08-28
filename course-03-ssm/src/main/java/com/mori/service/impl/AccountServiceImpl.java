package com.mori.service.impl;

import com.mori.domain.Account;
import com.mori.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Override
    public List<Account> findAll() {
        System.err.println("业务层：查询所有账户…");
        return null;
    }

    @Override
    public void saveAccount(Account account) {
        System.err.println("业务层：保存账户…");
    }
}
