package com.mori.test;

import com.mori.dao.RoleDao;
import com.mori.dao.UserDao;
import com.mori.domain.Role;
import com.mori.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Test
    @Transactional
    @Rollback(false)
    public void testAdd() {
        User user = new User();
        user.setUserName("小小");
        user.setAge(12);

        Role role = new Role();
        role.setRoleName("程序员");

        //配置关联关系(只用写一边即可，两边都写的话中间表会重复存导致报错)
        //若要解决，需要一方放弃中间表维护权
        user.getRoles().add(role);

        userDao.save(user);
        roleDao.save(role);
    }

    /**
     * 级联添加
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeAdd() {
        User user = new User();
        user.setUserName("小明");
        user.setAge(20);

        Role role = new Role();
        role.setRoleName("学生");

        user.getRoles().add(role);

        userDao.save(user);
    }

    /**
     * 级联删除
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeRemove() {
        User user = userDao.findOne(1L);
        userDao.delete(user);
    }
}
