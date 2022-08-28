package com.mori.test;

import com.mori.domain.Account;
import com.mori.mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMyBatis {
    @Test
    public void test() throws Exception {
        //加载MyBatis配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //创建对象
        SqlSession session = factory.openSession();
        //获取代理对象
        AccountMapper accountMapper = session.getMapper(AccountMapper.class);
        List<Account> accounts = accountMapper.findAll();
        System.err.println(accounts);
        session.close();
        is.close();
    }

    @Test
    public void test2() throws Exception {
        //加载MyBatis配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //创建对象
        SqlSession session = factory.openSession();
        //获取代理对象
        AccountMapper accountMapper = session.getMapper(AccountMapper.class);
        Account account = new Account();
        account.setName("小花");
        account.setMoney(12.5);

        //增删改需要提交事务
        accountMapper.saveAccount(account);
        session.commit();

        session.close();
        is.close();
    }
}
