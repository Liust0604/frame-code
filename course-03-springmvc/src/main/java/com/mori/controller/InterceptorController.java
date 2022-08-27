package com.mori.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 控制器
 * 测试springMVC的拦截器
 */
@Controller //先把控制类交给spring管理
@RequestMapping(path = "/intc")
public class InterceptorController {

    @RequestMapping(path = "/testInterceptor")
    public String testInterceptor() {
        System.out.println("testInterceptor！");
        return "success";
    }
}
