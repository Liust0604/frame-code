package com.mori.test;

import com.mori.dao.CustomerDao;
import com.mori.dao.LinkManDao;
import com.mori.domain.Customer;
import com.mori.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    LinkManDao linkManDao;

    /**
     * 主表配置关系/维护外键
     */
    @Test
    @Transactional //配置事务
    @Rollback(false) //SpringDataJPA 设置事务默认回滚，注解手动设置不自动回滚
    public void testAdd() {
        Customer customer = new Customer();
        customer.setCustName("百度");
        customer.setCustIndustry("IT");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小七");
        linkMan.setLkmPhone("13586326900");

        //主表配置关系
        customer.getLinkMans().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /**
     * 从表配置关系/维护外键
     */
    @Test
    @Transactional //配置事务
    @Rollback(false) //SpringDataJPA 设置事务默认回滚，注解手动设置不自动回滚
    public void testAdd2() {
        Customer customer = new Customer();
        customer.setCustName("阿里");
        customer.setCustIndustry("IT");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小八");
        linkMan.setLkmPhone("13586326900");

        //从表关系配置关系
        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /**
     * 主表放弃外键维护权
     */
    @Test
    @Transactional //配置事务
    @Rollback(false) //SpringDataJPA 设置事务默认回滚，注解手动设置不自动回滚
    public void testAdd3() {
        Customer customer = new Customer();
        customer.setCustName("腾讯");
        customer.setCustIndustry("IT");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小久");
        linkMan.setLkmPhone("13586326900");

        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }


    /**
     * 级联添加：保存一个客户的同时，保存所有联系人
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeAdd() {
        Customer customer = new Customer();
        customer.setCustName("百度A");
        customer.setCustIndustry("IT");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小久1");
        linkMan.setLkmPhone("13586326900");

        LinkMan linkMan2 = new LinkMan();
        linkMan2.setLkmName("小久2");
        linkMan2.setLkmPhone("13586326900");

        //主表、从表的实体类对象都要配置关联关系
        linkMan.setCustomer(customer);
        linkMan2.setCustomer(customer);
        customer.getLinkMans().add(linkMan);
        customer.getLinkMans().add(linkMan2);

        customerDao.save(customer);
    }

    /**
     * 级联删除：删除一个客户的同时，删除所有联系人
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeRemove() {
        Customer customer = customerDao.findOne(4L);
        customerDao.delete(customer);
    }
}
