package com.mori.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 控制器类
 */
@Controller //先把控制类交给spring管理
@RequestMapping(path = "/user")
public class HelloController {

    //注解 @RequestMapping ：请求映射，属性path，指定这个方法的请求路径
    @RequestMapping(path = "/hello", method = RequestMethod.GET, params = {"username"}, headers = {"Accept"})
    public String sayHello() {
        System.err.println("Hello World！");
        return "success";
    }
}
