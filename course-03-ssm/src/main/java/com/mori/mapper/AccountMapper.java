package com.mori.mapper;

import com.mori.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountMapper {
    /**
     * 查询所有账户信息
     *
     * @return
     */
    @Select("select * from ssm.account")
    List<Account> findAll();

    /**
     * 保存账户信息
     *
     * @param account
     */
    @Insert("insert into ssm.account(name,money) values (#{name},#{money})")
    void saveAccount(Account account);
}
