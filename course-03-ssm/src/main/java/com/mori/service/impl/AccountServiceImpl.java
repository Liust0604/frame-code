package com.mori.service.impl;

import com.mori.domain.Account;
import com.mori.mapper.AccountMapper;
import com.mori.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> findAll() {
        System.err.println("业务层：查询所有账户…");
        return accountMapper.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        System.err.println("业务层：保存账户…");
        accountMapper.saveAccount(account);
        //int a = 10 / 0;
    }
}
