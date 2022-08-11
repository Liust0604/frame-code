package com.mori.test;

import com.mori.demo.domain.Account;
import com.mori.demo.domain.QueryVo;
import com.mori.demo.domain.Role;
import com.mori.demo.domain.User;
import com.mori.demo.mapper.AccountMapper;
import com.mori.demo.mapper.RoleMapper;
import com.mori.demo.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream is;
    private SqlSession session;
    private UserMapper userMapper;
    private AccountMapper accountMapper;
    private RoleMapper roleMapper;

    @Before
    public void init() throws Exception {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        session = factory.openSession();
        //session = factory.openSession(true); //自动提交，通常是单个crud操作时使用
        userMapper = session.getMapper(UserMapper.class);
        accountMapper = session.getMapper(AccountMapper.class);
        roleMapper = session.getMapper(RoleMapper.class);
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

    /**
     * 查询操作
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //1、读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、构建者模式 构建工厂 SQLSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        //3、工厂模式 生产对象 SQLSession
        SqlSession session = factory.openSession();
        //4、代理模式，通过 SQLSession对象 对 Dao接口 的进行代理，增加操作sql的功能
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //5、使用代理对象，执行方法
        List<User> list = userMapper.findAll();
        System.err.println(list);
        //6、释放资源
        session.close();
        is.close();
    }

    /**
     * 保存操作
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("老李");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("上海浦西");
        System.err.println(user);
        //保存方法
        userMapper.saveUser(user);
        System.err.println(user); //sql执行后返回了id
    }

    /**
     * 修改操作
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(50);
        user.setUsername("老李");
        user.setSex("女");
        user.setBirthday(new Date());
        user.setAddress("北京");
        //修改方法
        userMapper.updateUser(user);
    }

    /**
     * 删除操作
     */
    @Test
    public void testDelete() {
        //删除方法
        userMapper.deleteUser(50);
    }

    /**
     * 查询一个操作
     */
    @Test
    public void testFindOne() {
        User user = userMapper.findById(51);
        System.err.println(user);
    }

    /**
     * 模糊查询操作
     */
    @Test
    public void testFindByName() {
        String name = "王";
        //String username = "%" + name + "%"; // 方式1、手动加上模糊查询的%
        String username = name; //方式2：使用固定写法${value} (里面必须写成value)，不常用
        List<User> users = userMapper.findByName(username);
        System.err.println(users);
    }

    /**
     * 查询数量操作
     */
    @Test
    public void testFindTotal() {
        int count = userMapper.findTotal();
        System.err.println(count);
    }

    /**
     * 根据包装对象查询操作
     */
    @Test
    public void testFindUserByVo() {
        User user = new User();
        user.setUsername("%二%");
        QueryVo vo = new QueryVo();
        vo.setUser(user);
        List<User> users = userMapper.findUserByVo(vo);
        System.err.println(users);
    }

    //动态sql

    /**
     * 根据条件查询
     */
    @Test
    public void testFindUserByCondition() {
        User user = new User();
        user.setUsername("小二王");
        user.setSex("男");
        List<User> users = userMapper.findUserByCondition(user);
        System.err.println(users);
    }

    /**
     * 根据id集合查询
     */
    @Test
    public void testFindUserByIds() {
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<>();
        list.add(42);
        list.add(46);
        list.add(51);
        vo.setIds(list);
        List<User> users = userMapper.findUserByIds(vo);
        System.err.println(users);
    }

    //多表查询

    /**
     * 一对多（用户和账户）
     */
    @Test
    public void testAccount() {
        //直接查询
        List<Account> list = accountMapper.findAll();
        System.err.println(list);
        System.err.println("=========");

        //多表查询
        //1、查询账户时，可以得到账户所属用户信息
        List<Account> accounts = accountMapper.findAllWithUser();
        for (Account account : accounts) {
            System.err.println(account);
        }
        System.err.println("=========");

        //2、查询用户时，可得到用户下所包含的账户信息
        List<User> users = userMapper.findAllWithAccount();
        for (User user : users) {
            System.err.println(user);
        }
        System.err.println("=========");
    }

    /**
     * 多对多（用户和角色）
     */
    @Test
    public void testRole() {
        //直接查询
        List<Role> list = roleMapper.findAll();
        System.err.println(list);
        System.err.println("=========");

        //多表查询
        //1、查询角色时，可以得到角色赋予的用户信息
        List<Role> roles = roleMapper.findAllWithUser();
        for (Role role : roles) {
            System.err.println(role);
        }
        System.err.println("=========");

        //2、查询用户时，可得到用户下所包含的账户信息
        List<User> users = userMapper.findAllWithRole();
        for (User user : users) {
            System.err.println(user);
        }
        System.err.println("=========");
    }
}
