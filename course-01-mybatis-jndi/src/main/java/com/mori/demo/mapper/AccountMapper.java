package com.mori.demo.mapper;

import com.mori.demo.domain.Account;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface AccountMapper {
    List<Account> findAll();

    List<Account> findAllWithUser();
}
