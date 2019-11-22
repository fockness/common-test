package com.example.demo.cases.common;

import org.junit.Test;
import org.omg.CORBA.INTERNAL;

import java.util.function.Function;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-5-10 10:46
 * 4
 * 所有标注了@FunctionalInterface注解的接口都是函数式接口，具体来说，所有标注了该注解的接口都将能用在lambda表达式上
 */
public class FunctionTest {

    @Test
    public void testApply(){
        Function<Integer, Integer> function = i -> i+1;//猜测可能是入参只有一个i，所以匹配到的是apply
        Function<Integer, String> function1 = i -> String.valueOf(i+1);//前一个是参数类型，后一个是返回值类型
        System.out.println(function.apply(7));
    }

    @Test
    public void testComposeAndThen(){
        Function<Integer, Integer> before = i -> i+2;
        Function<Integer, Integer> after = i -> i-9;
        //这种组合可以理解为，i传入，再i+2，i-9最后为i-7，apply时i=5，最后5-7=-2
        //System.out.println(after.compose(before).apply(5));

        /**
         * after.compose(before) ->i-7
         * i-7.compose(before) ->i-5
         * i-5.andThen(before) ->i-3
         */
        System.out.println(after.compose(before).compose(before).andThen(before).apply(5));
    }
}
