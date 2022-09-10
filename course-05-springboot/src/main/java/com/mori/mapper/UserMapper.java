package com.mori.mapper;

import com.mori.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    public List<User> queryUserList();
}
