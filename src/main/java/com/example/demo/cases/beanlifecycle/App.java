package com.example.demo.cases.beanlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class App {

    public static void main(String[] args) {
        log.info("Init application context");
        // create and configure beans
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "com.example.demo.cases.beanlifecycle");

        // retrieve configured instance
        User user = (User) context.getBean("user");

        // print info from beans
        log.info(user.toString());

        log.info("Shutdown application context");
        context.registerShutdownHook();
    }
}