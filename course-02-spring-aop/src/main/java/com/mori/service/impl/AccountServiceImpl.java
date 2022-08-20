package com.mori.service.impl;

import com.mori.service.AccountService;

/**
 * @author LiuShitian
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public void saveAccount() {
        System.out.println("执行了保存");
        int i = 1 / 0;
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("执行了更新");
    }

    @Override
    public int deleteAccount(int i) {
        System.out.println("执行了删除");
        return 0;
    }
}
