package com.mori.ui;

import com.mori.dao.AccountDao;
import com.mori.service.AccountService;
import com.mori.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

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
        //------ApplicationContext接口：立即加载，单例模式适用---------
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //ApplicationContext ac = new FileSystemXmlApplicationContext("D:\\IdeaProjects\\frame-code\\course-02-spring\\src\\main\\resources\\bean.xml");
        //2、根据id获取Bean对象
        AccountService accountService = (AccountService) ac.getBean("accountService");
        //AccountDao accountDao = ac.getBean("accountDao", AccountDao.class);

        //------BeanFactory接口：延迟加载，多例模式适用---------
        //    Resource resource = new ClassPathResource("bean.xml");
        //    InstanceFactory factory = new XmlBeanFactory(resource);
        //    AccountService accountService = (AccountService) factory.getBean("accountService");

        accountService.saveAccount();

        //多态，子类对象看成父类，就只能调用父类中的方法。这里要接口转回实现类，调用实现类中的close方法。
        //关闭spring容器，测试单例bean销毁方法
        ((ClassPathXmlApplicationContext) ac).close();
    }
}
