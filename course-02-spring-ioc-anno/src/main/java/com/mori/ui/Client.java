package com.mori.ui;

import com.mori.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟表现层，调用业务层
 */
public class Client {

    /**
     * 获取spring的IOC核心容器，并根据id获取对象
     *
     * @param args
     */
    public static void main(String[] args) {
        //1、获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2、根据id获取Bean对象
        AccountService accountService = (AccountService) ac.getBean("accountServiceImpl");

        accountService.saveAccount();

        //关闭spring容器，测试单例bean销毁方法
        ((ClassPathXmlApplicationContext) ac).close();
    }
}
