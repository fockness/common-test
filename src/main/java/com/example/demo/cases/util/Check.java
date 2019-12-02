package com.example.demo.cases.util;

import org.apache.commons.lang.StringUtils;

import java.util.Objects;

public class Check {

    public static void isNull(Object obj, String msg){
        if(Objects.isNull(obj)){
            throw new RuntimeException(msg);
        }
    }

    public static void isNull(String str, String msg){
        if(Objects.isNull(str) || str.trim().equals(StringUtils.EMPTY)){
            throw new RuntimeException(msg);
        }
    }
}
