package com.mori.factory;

import com.mori.service.AccountService;
import com.mori.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建Service的代理对象的工厂
 */
public class BeanFactory {

    private AccountService accountService;

    //set方式注入AccountService
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private TransactionManager txManager;

    //set方式注入TransactionManager
    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    /**
     * 获取Service的代理对象
     *
     * @return
     */
    public AccountService getAccountService() {
        AccountService accountServiceProxy = (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务的支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            //1、开启事务
                            txManager.begin();
                            //2、jdbc操作
                            Object rtValue = method.invoke(accountService, args);
                            //3、提交事务
                            txManager.commit();
                            //4、返回结果
                            return rtValue;
                        } catch (Exception e) {
                            //回滚
                            txManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            //释放连接
                            txManager.release();
                        }
                    }
                });
        return accountServiceProxy; //返回代理对象
    }

}
