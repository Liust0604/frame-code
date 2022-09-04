package com.mori.test;

import com.mori.domain.Customer;
import com.mori.utils.JpaUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql 查询语言
 */
public class JpqlTest {

    EntityManager em;
    EntityTransaction tx;

    @Before
    public void init() {
        em = JpaUtils.getEntityManager();
        tx = em.getTransaction();
        tx.begin();
    }

    @After
    public void close() {
        tx.commit();
        em.close();
    }

    private void show(List<Customer> list) {
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 查询全部
     */
    @Test
    public void testFindAll() {
        String jpql = "from com.mori.domain.Customer";
        //创建Query对象，query对象才是执行jpql的对象
        Query query = em.createQuery(jpql);

        //发送查询，并封装结果集
        List<Customer> list = query.getResultList();
        show(list);
    }

    /**
     * 排序查询
     */
    @Test
    public void testOrder() {
        String jpql = "from Customer order by custId desc";
        Query query = em.createQuery(jpql);

        List<Customer> list = query.getResultList();
        show(list);
    }

    /**
     * 统计
     */
    @Test
    public void testCount() {
        String jpql = "select count(custId) from Customer";
        Query query = em.createQuery(jpql);

        Long count = (Long) query.getSingleResult();
        System.out.println(count);
    }

    /**
     * 分页
     */
    @Test
    public void testPage() {
        String jpql = "from Customer";
        Query query = em.createQuery(jpql);
        //对参数赋值【分页】
        query.setFirstResult(0); //开始索引
        query.setMaxResults(2); //每页条数

        List<Customer> list = query.getResultList();
        show(list);
    }

    /**
     * 条件查询
     */
    @Test
    public void testCondition() {
        String jpql = "from Customer where custName like ? ";
        Query query = em.createQuery(jpql);
        //对参数赋值【占位符】（占位符索引1开始，参数值）
        query.setParameter(1, "小%");

        List<Customer> list = query.getResultList();
        show(list);
    }

}
