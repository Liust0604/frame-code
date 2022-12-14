package com.mori.service.impl;


import com.mori.mapper.AccountDao;
import com.mori.domain.Account;
import com.mori.service.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.*;

/**
 * 模拟业务层
 * <p>
 * 基于XML文件的IOC配置
 * <bean id="accountService" class="com.mori.service.impl.AccountServiceImpl" //创建对象
 * scope="singleton"  //作用范围
 * init-method="init" destroy-method="destory">  //生命周期
 * <property name="" value="" | ref="" ></property>  //DI 依赖注入
 * </bean>
 */
//@Component
@Service
@Scope("singleton")
public class AccountServiceImpl implements AccountService {

    //类依赖的属性
    @Value("10")
    private Integer age; //基本数据类型的包装类
    @Value("小明")
    private String name; //String类型
    private Date birthday; //bean类型

    //@Autowired
    //@Qualifier("accountDaoImpl")
    @Resource(name = "accountDaoImpl")
    AccountDao accountDao;

    public AccountServiceImpl() {
        System.err.println("对象创建了");
    }

    @PostConstruct
    public void init() {
        System.err.println("对象初始化…");
    }

    @PreDestroy
    public void destory() {
        System.err.println("对象销毁…");
    }


    @Override
    public void saveAccount() {
        System.err.println("AccountService的saveAccount方法执行了……  " + name + " , " + age + " , " + birthday);
        accountDao.saveAccount();
    }

    /*案例*/
    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }

}
