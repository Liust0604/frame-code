package com.mori.dao;

import com.mori.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * JpaRepository< 操作的实体类类型 , 实体类中主键属性的类型 >
 *  * 封装了基本CRUD操作
 * JpaSpecificationExecutor< 操作的实体类类型 >
 *  * 封装了复杂查询(分页)
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

}
