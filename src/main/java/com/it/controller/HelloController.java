package com.it.controller;

import com.it.pojo.Person;
import com.it.service.ExportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class HelloController {

    @Resource
    private ExportExcelService exportExcelService;

    @RequestMapping("/hello")
    public String hello(HttpServletResponse response){
        try {
            exportExcelService.exportStudentInfoExcel(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Helle World!";
    }

    /**
     * ******************************************************************************************************
     * @Value 方式获取值，适用于简单的值获取
     */
    // 获取配置中的值，key: value形式,@Value只能获取这种简单的
    @Value("${name}")
    private String name;

    // 获取数组中第一个值
    @Value("${cities[0]}")
    private String city;

    // 获取学生集合中第一个学生的年龄
    @Value("${students[0].age}")
    private String age;

    // 获取maps中的name值
    @Value("${maps.name}")
    private String mapName;

    // 获取person中的name值
    @Value("${person.name}")
    private String personName;

    /**
     * **********************************************************************************************************
     *
     */

    @RequestMapping("/getValue")
    public String showValue(){
        System.out.println("name:"+name);
        return "Helle World!";
    }

    /**
     *  通过环境对象 Environment 获取值
     * *****************************************************************************************************************
     */

    /**
     * 使用 @ConfigurationProperties方式获取值：
     * ******************************************************************************************************************************
     * /**
     *  * 使用 @ConfigurationProperties方式获取值：
     *  * 　　　　创建POJO，并使用注解@Component将pojo交给spring来管理；
     *  * 　　　　使用注解@ConfigurationProperties来建立pojo和配置文件的映射;
     *  * 　　　　在类中注入POJO，通过pojo的get方法来获取对应的值。
     *
     */

    @Autowired
    private Person person;

    @Autowired
    private Environment environment;
    @RequestMapping("/getArray")
    public String showArray(){
        System.out.println("city:"+city);
        System.out.println("age:"+age);
        System.out.println("mapName:"+mapName);
        System.out.println("personName:"+personName);
        System.out.println("*************************以下是Environment对象获取方式*********************************");
        System.out.println("Environment对象获取name值："+environment.getProperty("name"));
        System.out.println("Environment对象获取person属性值："+environment.getProperty("person.age"));
        System.out.println("*************************以下是@ConfigurationProperties获取方式*********************************");
        System.out.println("@ConfigurationProperties对象获取person属性值："+person.getName()+":"+person.getAge());

        return "Success!";
    }





}
