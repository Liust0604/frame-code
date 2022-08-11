package com.mori.demo.mapper;

import com.mori.demo.domain.User;
import com.mori.mybatis.annotations.Select;

import java.util.List;

/**
 * 用户的持久层接口
 * (使用Mybatis框架，不用写实现类。通过SQLSessionFactory得到的SQLSession对象，创建接口的代理对象)
 */
public interface UserMapper {

    @Select("select * from eesy_mybatis.user")
    List<User> findAll();
}
