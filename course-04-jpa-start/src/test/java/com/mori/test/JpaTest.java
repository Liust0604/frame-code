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
}
