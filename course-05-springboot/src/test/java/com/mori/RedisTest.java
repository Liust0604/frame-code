package com.mori;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mori.pojo.Student;
import com.mori.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class RedisTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void test() throws Exception {
        //1、从redis获取数据（json字符串）
        String userListJson = redisTemplate.boundValueOps("student.findAll").get();

        //2、判断redis是否存在数据
        if (userListJson == null) {
            //3、不存在，从数据库查询
            List<Student> students = studentRepository.findAll();
            //查询到的结果转为json字符串（springMVC环境默认集成jackson）
            ObjectMapper mapper = new ObjectMapper();
            userListJson = mapper.writeValueAsString(students);
            //数据存入redis
            redisTemplate.boundValueOps("student.findAll").set(userListJson);
            System.err.println("=====从数据库获得数据====");
        } else {
            System.err.println("=====从Redis获得数据====");
        }

        //4、业务操作
        System.err.println(userListJson);
    }
}
