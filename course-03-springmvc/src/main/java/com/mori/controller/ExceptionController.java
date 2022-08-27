package com.mori.controller;

import com.mori.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 控制器
 * 测试 异常处理
 */
@Controller
@RequestMapping(path = "/exception")
public class ExceptionController {

    @RequestMapping("/testException")
    public String testException() throws Exception {
        //模拟异常
        try {
            int a = 10 / 0;
        } catch (Exception e) {
            //打印异常信息
            e.printStackTrace();
            //抛出自定义异常信息
            throw new SysException("计算错误…");
        }
        return "success";
    }
}
