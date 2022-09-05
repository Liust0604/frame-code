package com.mori.test;

import com.mori.dao.CustomerDao;
import com.mori.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml") //指定spring容器配置信息
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindOne() {
        Customer customer = customerDao.findOne(4L);
        System.err.println(customer);
    }

    /**
     * Save:传递对象没有主键id，代表保存；有主键id，代表更新
     * （最好先查询，再更新，否则没有的数据会置空）
     */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustId(6L);
        customer.setCustName("NANA");
        customer.setCustIndustry("研究生");
        customer.setCustLevel("vip");
        customerDao.save(customer);
    }

    @Test
    public void testDelete() {
        customerDao.delete(3L);
    }

    @Test
    public void testFindAll() {
        List<Customer> customers = customerDao.findAll();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Test
    public void testCount() {
        long count = customerDao.count();//查询全部数量
        System.out.println(count);
    }

    @Test
    public void testExists() {
        boolean exist = customerDao.exists(6L); //判断是否存在
        System.err.println(exist);
    }

    @Test
    @Transactional //保证 getOne正常运行
    public void testGetOne() {
        Customer one = customerDao.getOne(4L);
        System.out.println(one);
    }
}
