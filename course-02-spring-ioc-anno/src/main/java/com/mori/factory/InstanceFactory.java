package com.mori.factory;

import com.mori.dao.impl.AccountDaoImpl;
import com.mori.service.impl.AccountServiceImpl;

/**
 * 模拟一个工厂类，创建实例对象。
 * 这些类可能是存在于jar包的字节码文件，无法直接修改源码
 */
public class InstanceFactory {

    public AccountServiceImpl getAccountService() {
        return new AccountServiceImpl();
    }

    //静态方法
    public static AccountServiceImpl getAccountServiceStatic() {
        return new AccountServiceImpl();
    }
}
