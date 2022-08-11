package com.mori.test;

import com.mori.demo.domain.Account;
import com.mori.demo.domain.User;
import com.mori.demo.mapper.AccountMapper;
import com.mori.demo.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream is;
    private SqlSession session;
    private SqlSessionFactory factory;
    private UserMapper userMapper;
    private AccountMapper accountMapper;

    @Before
    public void init() throws Exception {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(is);
        session = factory.openSession();
        //session = factory.openSession(true); //自动提交，通常是单个crud操作时使用
        userMapper = session.getMapper(UserMapper.class);
        accountMapper = session.getMapper(AccountMapper.class);
    }

    @After
    public void close() throws Exception {
        // 手动提交事务
        // 若初始化时 session = factory.openSession(true); 则会自动提交,session.commit()手动提交可省略
        session.commit();
        //释放资源
        session.close();
        is.close();
    }

    @Test
    public void testJDBC() {
        System.err.println("========查询");
        List<User> list = userMapper.findAll();
        System.err.println(list);
        System.err.println("========查询一个用户");
        User u = userMapper.findById(52);
        System.err.println(u);
        System.err.println("========模糊查询");
        List<User> users = userMapper.findByName("%二%");
        System.err.println(users);
        System.err.println("========查询总数");
        int count = userMapper.findTotal();
        System.err.println(count);
        System.err.println("========添加");
        User user = new User();
        user.setUsername("老李");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("上海浦西");
        //userMapper.saveUser(user);
        System.err.println("========修改");
        user.setId(55);
        user.setAddress("广东");
        //userMapper.updateUser(user);
        System.err.println("========删除");
        //userMapper.deleteUser(55);
    }


    //多表查询

    /**
     * 一对多 （查账户，关联所属用户）
     */
    @Test
    public void testAccount() {
        //1、（多查一）查询账户时，可以得到账户所属用户信息
        List<Account> accounts = accountMapper.findAllWithUser();
        for (Account account : accounts) {
            System.err.println(account);
        }
    }

    /**
     * 一对多 （查用户，关联所有账户）
     */
    @Test
    public void testUser() {
        //2、（一查多）查询用户时，可得到用户下所包含的账户信息
        List<User> users = userMapper.findAllWithAccount();
        for (User user : users) {
            System.err.println(user);
        }
    }


    /**
     * Mybatis的二级缓存（SQLSessionFactory的缓存）
     * <p>
     * 一级缓存SQLSession，Mybatis自己就存在，同一个SQLSession同一个数据库查询操作，结果对象会保存，只查询第一次。
     * 所以一级缓存不用担心，只需要关注二级缓存
     */
    @Test
    public void testSecondLevelCache() {
        //由同一个SQLSessionFactory创建两个SQLSession
        SqlSession session1 = factory.openSession();
        UserMapper userMapper1 = session1.getMapper(UserMapper.class);
        SqlSession session2 = factory.openSession();
        UserMapper userMapper2 = session2.getMapper(UserMapper.class);

        //使用二级缓存，需要先全局、映射、select操作，三步开启支持二级缓存。否则无效，两个user各查询各的，结果也不同
        //开启二级缓存后查询，只执行第一次sql，但是结果不一样
        //原因：二级缓存存放的是数据，而不是对象。即session会创建一个新的用户，将二级缓存的数据填充到新用户中
        User user1 = userMapper1.findById(41);
        System.err.println(user1.hashCode());
        session1.close();

        User user2 = userMapper2.findById(41);
        System.err.println(user2.hashCode());
        session2.close();

    }
}
