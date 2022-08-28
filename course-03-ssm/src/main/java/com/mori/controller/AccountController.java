package com.mori.controller;

import com.mori.domain.Account;
import com.mori.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/findAll")
    public String findAll() {
        System.err.println("控制层：查询所有账户…");
        accountService.findAll();
        return "success";
    }

    @RequestMapping("/saveAccount")
    public String saveAccount(Account account) {
        System.err.println("控制层：保存账户…");
        return "success";
    }
}
