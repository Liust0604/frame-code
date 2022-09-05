package com.mori.dao;

import com.mori.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaRepository< 操作的实体类类型 , 实体类中主键属性的类型 >
 * * 封装了基本CRUD操作
 * JpaSpecificationExecutor< 操作的实体类类型 >
 * * 封装了复杂查询(分页)
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    //jpql形式查询

    /**
     * 根据客户名称查询客户
     *
     * @param customerName
     * @return
     */
    @Query(value = "from Customer where custName = ?")
    Customer findByName(String customerName);

    /**
     * 根据客户名称和客户id查询客户
     *
     * @param name
     * @param id
     * @return
     */
    @Query(value = "from Customer where custName = ?1 and custId = ?2")
    Customer findByNameAndId(String name, Long id);

    /**
     * 更新
     */
    @Query(value = "update Customer set custName = ? where custId = ?")
    @Modifying
    //声明是更新操作
    void updateName(String name, Long id);

    //sql形式查询

    /**
     * nativeQuery : true 指定为本地查询（sql查询）
     *
     * @return
     */
    //@Query(value = "select * from cst_customer", nativeQuery = true)
    @Query(value = "select * from cst_customer where cust_name like ?", nativeQuery = true)
    List<Object[]> findSql(String name);

    //jpa命名规则查询

    Customer findByCustName(String name);

}
