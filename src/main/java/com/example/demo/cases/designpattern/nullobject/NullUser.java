package com.example.demo.cases.designpattern.nullobject;

import org.apache.commons.lang3.StringUtils;

public class NullUser extends AbstractUser {

    @Override
    public Boolean isNull() {
        return true;
    }

    @Override
    public String predicate() {
        return StringUtils.EMPTY;
    }
}
