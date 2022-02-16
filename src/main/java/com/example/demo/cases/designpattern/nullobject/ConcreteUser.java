package com.example.demo.cases.designpattern.nullobject;

import org.apache.commons.lang3.StringUtils;

public class ConcreteUser extends AbstractUser {

    @Override
    public Boolean isNull() {
        return false;
    }

    @Override
    public String predicate() {
        return StringUtils.EMPTY;
    }
}
