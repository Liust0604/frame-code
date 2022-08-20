package com.mori.service.impl;


import com.mori.dao.AccountDao;
import com.mori.dao.impl.AccountDaoImpl;
import com.mori.domain.Account;
import com.mori.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * 模拟业务层
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    //类依赖的属性
    private Integer age; //基本数据类型的包装类
    private String name; //String类型
    private Date birthday; //bean类型
    //集合类型
    private String[] myArr;
    private List<String> myList;
    private Set<String> mySet;
    private Map<String, String> myMap;
    private Properties myProps;

    public AccountServiceImpl(Integer age, String name, Date birthday) {
        this.age = age;
        this.name = name;
        this.birthday = birthday;
    }

    public AccountServiceImpl() {
        System.err.println("对象创建了");
    }

    public void init() {
        System.err.println("对象初始化…");
    }

    public void destory() {
        System.err.println("对象销毁…");
    }

    @Override
    public void saveAccount() {
        System.err.println("AccountService的saveAccount方法执行了……  " + name + " , " + age + " , " + birthday);
        System.err.println("myArr=" + Arrays.toString(myArr));
        System.err.println("myList=" + myList);
        System.err.println("mySet=" + mySet);
        System.err.println("myMap=" + myMap);
        System.err.println("myProps=" + myProps);
        accountDao.saveAccount();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setMyArr(String[] myArr) {
        this.myArr = myArr;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMyProps(Properties myProps) {
        this.myProps = myProps;
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
