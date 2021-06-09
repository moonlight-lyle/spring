package com.it.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 使用 @ConfigurationProperties方式获取值：
 * 　　　　创建POJO，并使用注解@Component将pojo交给spring来管理；
 * 　　　　使用注解@ConfigurationProperties来建立pojo和配置文件的映射;
 * 　　　　在类中注入POJO，通过pojo的get方法来获取对应的值。
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person implements Serializable {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
