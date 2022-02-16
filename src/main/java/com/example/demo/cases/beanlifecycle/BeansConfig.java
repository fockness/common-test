package com.example.demo.cases.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("BeansConfig")
public class BeansConfig {

    @Bean(name = "user", initMethod = "doInit", destroyMethod = "doDestroy")
    public User create() {
        User user = new User();
        user.setName("pdai");
        user.setAge(18);
        return user;
    }
}