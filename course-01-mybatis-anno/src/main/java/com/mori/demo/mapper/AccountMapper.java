package com.mori.demo.mapper;

import com.mori.demo.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface AccountMapper {

    @Select("select * from eesy_mybatis.account")
    List<Account> findAll();

    //查一时，通常是立即查询，FetchType.EAGER 立即查询
    @Select("select * from eesy_mybatis.account")
    @Results(id = "accountMap", value = {
            @Result(column = "uid", property = "user", one = @One(select = "com.mori.demo.mapper.UserMapper.findById", fetchType = FetchType.EAGER))
    })
    List<Account> findAllWithUser();

    List<Account> findAllWithUserLazy();

    @Select("select * from eesy_mybatis.account where uid = #{userId}")
    List<Account> findByUid(Integer userId);
}
