package com.mori.test;

import com.mori.domain.Customer;
import com.mori.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    /**
     * 测试jpa的保存
     */
    @Test
    public void testSave() {
        //1、加载配置文件，创建实体管理器工厂对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa"); //填入持久化单元名称
        //2、通过工厂对象获取实体管理器
        EntityManager em = factory.createEntityManager();
        //3、获取事务对象
        EntityTransaction tx = em.getTransaction();
        //4、开启事务
        tx.begin();
        //5、完成增删改查操作
        Customer customer = new Customer();
        customer.setCustName("小明");
        customer.setCustIndustry("教育");
        em.persist(customer); //保存操作
        //6、提交事务/回滚事务
        tx.commit();
        //7、释放资源
        em.close();
        factory.close();
    }

    @Test
    public void testSave2() {
        //JpaUtils通过公共工厂获取实体管理器
        EntityManager em = JpaUtils.getEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = new Customer();
        customer.setCustName("小明");
        customer.setCustIndustry("教育");
        em.persist(customer); //保存操作
        tx.commit();

        //释放资源
        em.close();
        //公共工厂，不关闭
        //factory.close();
    }

    /**
     * 根据id查询客户
     */
    @Test
    public void testFind() {
        EntityManager em = JpaUtils.getEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //find()，根据id查数据
        //参数：class 查询结果封装的实体类字节码；id 主键值
        //对象本身、立即加载
        Customer customer = em.find(Customer.class, 2L);
        System.err.println(customer);
        tx.commit();

        em.close();
    }

    /**
     * 根据id查询客户
     */
    @Test
    public void testReference() {
        EntityManager em = JpaUtils.getEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //代理对象、延迟加载
        Customer customer = em.getReference(Customer.class, 2L);
        System.err.println(customer);
        tx.commit();

        em.close();
    }

    /**
     * 删除
     */
    @Test
    public void testRemove() {
        EntityManager em = JpaUtils.getEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //根据id查询客户
        Customer customer = em.find(Customer.class, 2L);
        //删除
        em.remove(customer);
        tx.commit();

        em.close();
    }

    /**
     * 更新
     */
    @Test
    public void testMerge() {
        EntityManager em = JpaUtils.getEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = new Customer();
        customer.setCustId(1L);
        customer.setCustName("小非");
        customer.setCustIndustry("服务业");
        //修改
        em.merge(customer);
        tx.commit();

        em.close();
    }


}
