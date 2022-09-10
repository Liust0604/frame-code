package com.mori.controller;

import com.mori.domain.User;
import com.mori.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MybatisController {

    @Autowired
    private UserMapper userMapper;

    @ResponseBody
    @RequestMapping("/queryUserList")
    public List<User> queryUserList() {
        return userMapper.queryUserList();
    }
}
