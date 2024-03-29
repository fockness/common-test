package com.example.demo.controller;

import com.example.demo.cases.entity.dto.AddValidationGroup;
import com.example.demo.cases.entity.dto.EditValidationGroup;
import com.example.demo.cases.entity.dto.UserAddDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class HelloController {

//    @Autowired
//    private MsgProducer msgProducer;

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

    /*@PostMapping("/test/send")
    public String testSendMsg(){
        msgProducer.sendMsg(ExchangeConstant.EXCHANGE_A, RoutingKeyConstant.ROUTINGKEY_A, "AAA");
        return "send";
    }*/

    @PostMapping("/user")
    public String addUser(@Validated(AddValidationGroup.class) @RequestBody UserAddDTO obj) {
        return "test";
    }

    @PutMapping("/user")
    public String updateUser(@Validated(EditValidationGroup.class) @RequestBody UserAddDTO obj) {
        return "test";
    }
}