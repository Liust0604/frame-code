package com.mori.controller;

import com.mori.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping("/findAll")
    public String findAll() {
        System.err.println("控制层：查询所有账户…");
        return "success";
    }

    @RequestMapping("/saveAccount")
    public String saveAccount(Account account) {
        System.err.println("控制层：保存账户…");
        return "success";
    }
}
