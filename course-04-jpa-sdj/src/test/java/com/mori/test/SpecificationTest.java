package com.mori.test;

import com.mori.dao.CustomerDao;
import com.mori.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml") //指定spring容器配置信息
public class SpecificationTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据条件查询单个对象
     */
    @Test
    public void testFindOne() {
        //构建查询条件
        /**
         *  自定义查询条件
         * 1、（匿名内部类）实现Specification接口，提供查询对象的泛型
         * 2、实现toPredicate方法，构造查询条件
         * 3、两个参数： root 获取需要查询对象的属性
         *            CriteriaBuilder 构造查询条件，封装了很多查询条件（模糊匹配、精准匹配……）
         * 查询条件：
         * 1、查询方式 cb；2、比较的属性名称 root
         */
        //需求，根据客户名查询客户
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1、获取比较的属性
                Path<Object> custName = root.get("custName");
                //2、构造查询条件（比较的属性名，属性值）
                Predicate predicate = cb.equal(custName, "NANA");//精准匹配
                return predicate;
            }
        };
        //传递查询条件Specification查询
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 多条件查询
     */
    @Test
    public void testFind() {
        /**
         * 1、（匿名内部类）实现Specification接口
         * 2、实现toPredicate方法
         * 3、root指定要查询的属性,CriteriaBuilder指定查询条件
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1、获取要匹配的属性
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                //2、配置查询条件
                Predicate p1 = cb.equal(custName, "小明");//精准匹配（比较的属性名，属性值）
                Predicate p2 = cb.equal(custIndustry, "教育");
                //3、联合查询条件（与/或）
                Predicate p = cb.and(p1, p2); //以与的形式拼接多个查询条件
                return p;
            }
        };
        Customer one = customerDao.findOne(spec);
        System.out.println(one);
    }

    /**
     * 模糊查询
     */
    @Test
    public void testFind2() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate p = cb.like(custName.as(String.class), "小%");
                return p;
            }
        };
        List<Customer> all = customerDao.findAll();
        System.out.println(all);
    }

    /**
     * 排序
     */
    @Test
    public void testSort() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        //创建排序对象(顺序，排序的属性名…)
        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> list = customerDao.findAll(spec, sort);
        System.out.println(list);
    }

    /**
     * 分页
     */
    @Test
    public void testPage() {
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate p = cb.like(custName.as(String.class), "小%");
                return p;
            }
        };
        //1、创建一个分页对象
        //PageRequest是Pageable接口的实现类（当前查询页数0开始，每页数量）
        Pageable pageable = new PageRequest(0, 2);
        //返回结果是SpringDataJPA封装好的pageBean对象
        Page<Customer> page = customerDao.findAll(spec, pageable);
        //findAll(pageable)
        List<Customer> list = page.getContent(); //查询到的记录
        long totalCount = page.getTotalElements(); //根据查询条件查到的总记录数
        long totalPage = page.getTotalPages(); //查询到的总记录，可以分成总页数
        System.out.println(list);
        System.out.println(totalCount);
        System.out.println(totalPage);
    }
}
