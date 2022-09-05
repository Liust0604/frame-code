package com.mori.test;

import com.mori.dao.CustomerDao;
import com.mori.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml") //指定spring容器配置信息
public class FunNameTest {

    //JPA 方法命名规则

    @Autowired
    private CustomerDao customerDao;

    /**
     *
     */
    @Test
    public void test() {
        Customer customer = customerDao.findByCustName("小明");
        System.out.println(customer);
    }
}
