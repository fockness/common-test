package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HelloController {

    @GetMapping("/helloSpringBoot")
    public String HelloSpring (){
        return "hello spring boot";
    }

    @GetMapping("/test/liquibase")
    public String liquibase() throws IOException, InterruptedException {
        String path = this.getClass().getResource("/").getPath();
        path = path.substring(0, path.lastIndexOf("/target"));
        path = path.replaceFirst("/", "");
        path = "\"" + path + "/src/main/resources/liquibase/version/v2.0.0/liquibase-v2.0.0.xml\"";
        System.out.println(path);
        String liquibase = "liquibase --changeLogFile=" + path + "  rollback";
        String[] cmd = { liquibase };
        Process p = Runtime.getRuntime().exec(cmd);
        p.waitFor();
        return "test";
    }
}