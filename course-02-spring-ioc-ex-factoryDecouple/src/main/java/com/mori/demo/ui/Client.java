package com.mori.demo.ui;

import com.mori.demo.factory.BeanFactory;
import com.mori.demo.service.AccountService;

/**
 * 模拟表现层，调用业务层
 */
public class Client {

    public static void main(String[] args) {
        //AccountService accountService = new AccountServiceImpl();
        AccountService accountService = (AccountService) BeanFactory.getBean("accountService");
        accountService.saveAccount();
    }
}
