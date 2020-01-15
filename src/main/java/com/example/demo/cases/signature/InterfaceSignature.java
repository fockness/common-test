package com.example.demo.cases.signature;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 */
@Target({TYPE, METHOD})
@Retention(RUNTIME)
@Documented
public @interface InterfaceSignature {
    boolean resubmit() default true;//允许重复请求
}