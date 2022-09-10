package com.mori;

import com.mori.pojo.Student;
import com.mori.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class JPATest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void test() {
        List<Student> students = studentRepository.findAll();
        System.out.println(students);
    }
}
