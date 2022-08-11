package com.mori.demo.mapper;

import com.mori.demo.domain.Role;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface RoleMapper {
    List<Role> findAll();

    List<Role> findAllWithUser();
}
