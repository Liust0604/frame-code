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
    private SqlSessionFactory factory;
    private UserMapper userMapper;
    private AccountMapper accountMapper;
    private RoleMapper roleMapper;

    @Before
    public void init() throws Exception {
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(is);
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
     * <p>
     * 注意：查到的关联主表对象/从表对象列表，其中对象只是基础属性，不会再继续关联查询。
     * 即，查账户时，查到所属的用户，user为null
     * 即，查用户时，查到账户列表，accounts为null
     */
    @Test
    public void testAccount() {
        //直接查询
        List<Account> list = accountMapper.findAll();
        System.err.println(list);
        System.err.println("=========");

        //多表查询
        //1、（多查一）查询账户时，可以得到账户所属用户信息
        List<Account> accounts = accountMapper.findAllWithUser();
        for (Account account : accounts) {
            System.err.println(account);
        }
        System.err.println("=========");

        //2、（一查多）查询用户时，可得到用户下所包含的账户信息
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
        //1、（左查右）查询角色时，可以得到角色赋予的用户信息
        List<Role> roles = roleMapper.findAllWithUser();
        for (Role role : roles) {
            System.err.println(role);
        }
        System.err.println("=========");

        //2、（右查左）查询用户时，可得到用户下所包含的账户信息
        List<User> users = userMapper.findAllWithRole();
        for (User user : users) {
            System.err.println(user);
        }
        System.err.println("=========");
    }


    /**
     * 延迟加载
     */
    @Test
    public void testAccountLazy() {
        //(查一) 实现association延迟加载
        List<Account> accounts = accountMapper.findAllWithUserLazy();
        for (Account account : accounts) {
            System.err.println(account);
        }
        System.err.println("=========");
        //2、（查多）实现collection延迟加载
        List<User> users = userMapper.findAllWithAccountLazy();
        for (User user : users) {
            System.err.println(user);
        }
        System.err.println("=========");
    }

    /**
     * Mybatis的一级缓存（SQLSession的缓存）
     */
    @Test
    public void testFirstLevelCache() {
        User user1 = userMapper.findById(41);
        System.err.println(user1.hashCode());
        //第二次相同查询时，不会再同样的执行SQL语句，查询结果相同(哈希地址值一致)
        User user2 = userMapper.findById(41);
        System.err.println(user2.hashCode());

        //清空缓存1：关闭SQLSession，并再次获取
//        session.close();
//        session = factory.openSession();
//        userMapper = session.getMapper(UserMapper.class);

        //清空缓存2：
        session.clearCache();

        //SQLSession 关闭后，不再有一级缓存。会重新sql查询，结果为新的哈希地址值
        User user3 = userMapper.findById(41);
        System.err.println(user3.hashCode());
    }

    /**
     * 测试缓存的同步(即数据库数据变化时，清除内存缓存，结果以数据库为准)
     */
    @Test
    public void testClearCache() {
        User user1 = userMapper.findById(53);
        System.err.println(user1.hashCode());
        user1.setAddress("南京");
        //修改数据库数据
        userMapper.updateUser(user1);

        User user2 = userMapper.findById(53);
        System.err.println(user2.hashCode());
    }

    /**
     * Mybatis的二级缓存（SQLSessionFactory的缓存）
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
