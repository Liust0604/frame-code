package com.mori.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Quick2Controller {

    @Value("${name}")
    private String name;

    @Value("${person.age}")
    private String age;

    @Value("${city[0]}")
    private String city;

    @RequestMapping("/config")
    public String config() {
        return name + "  ,  " + age + "  ,  " + city;
    }
}
