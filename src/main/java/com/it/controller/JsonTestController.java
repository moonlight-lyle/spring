package com.it.controller;

import com.alibaba.fastjson.JSON;
import com.it.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class JsonTestController {

    @PostMapping("/json")
    public String hello(){
        String param="{\n" +
                "    \"name\":\"zs\",\n" +
                "    \"age\":\"18\"\n" +
                "}";
        if (!StringUtils.isEmpty(param)){
            Person person = JSON.parseObject(param, Person.class);
            System.out.println(person.getName()+":"+person.getAge());
        }
        return "Helle World!";
    }









}
