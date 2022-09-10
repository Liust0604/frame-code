package com.mori.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuickController {

    @ResponseBody
    @RequestMapping("/quick")
    public String quick() {
        return "Hello SpringBoot！";
    }
}
