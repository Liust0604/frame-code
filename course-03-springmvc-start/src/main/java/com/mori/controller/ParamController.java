package com.mori.controller;

import com.mori.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 控制器类
 */
@Controller
public class ParamController {

    /**
     * 绑定参数
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(path = "/param", params = {"username", "password"})
    public String testParam(String username, String password) {
        System.err.println(username + " , " + password);
        return "success";
    }

    /**
     * 把参数数据封装到JavaBean的类中
     *
     * @return
     */
    @RequestMapping(path = "/paramBean")
    public String testParamBean(Account account) {
        System.err.println(account);
        return "success";
    }

    /**
     * 获取原生Servlet的API
     *
     * @return
     */
    @RequestMapping(path = "/servletAPI")
    public String testServlet(HttpServletRequest request, HttpServletResponse response) {
        System.err.println("request：" + request);
        HttpSession session = request.getSession();
        ServletContext ctx = session.getServletContext();
        System.err.println("session：" + session);
        System.err.println("ctx：" + ctx);
        System.err.println("response：" + response);
        return "success";
    }
}
