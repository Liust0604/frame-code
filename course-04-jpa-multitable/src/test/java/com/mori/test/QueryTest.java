package com.mori.test;

import com.mori.dao.CustomerDao;
import com.mori.dao.LinkManDao;
import com.mori.domain.Customer;
import com.mori.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class QueryTest {
    @Autowired
    CustomerDao customerDao;

    @Autowired
    LinkManDao linkManDao;

    @Test
    @Transactional //解决java中的 no session问题
    public void testQuery1() {
        Customer customer = customerDao.findOne(1L);
        //对象导航查询
        //查询此客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();
        for (LinkMan linkMan : linkMans) {
            System.out.println(linkMan);
        }
    }

    @Test
    @Transactional //解决java中的 no session问题
    public void testQuery2() {
        LinkMan linkMan = linkManDao.findOne(2L);
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }
}
