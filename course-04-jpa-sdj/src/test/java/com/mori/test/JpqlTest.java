package com.mori.test;

import com.mori.dao.CustomerDao;
import com.mori.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml") //指定spring容器配置信息
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindByName() {
        Customer customer = customerDao.findByName("小明");
        System.out.println(customer);
    }

    @Test
    public void testFindByNameAndId() {
        Customer customer = customerDao.findByNameAndId("NaNa", 6L);
        System.out.println(customer);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdate() {
        customerDao.updateName("果果", 4L);
    }

    @Test
    public void testFindSql() {
        List<Object[]> list = customerDao.findSql("小%");
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }
}
