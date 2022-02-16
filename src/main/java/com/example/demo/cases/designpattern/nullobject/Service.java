package com.example.demo.cases.designpattern.nullobject;

public class Service {

    public AbstractUser getUser(Integer age){
        return age > 10 ? new ConcreteUser() : new NullUser();
    }
}
