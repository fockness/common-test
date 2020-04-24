package com.example.demo.cases.common;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-5-9 17:30
 * 4
 */

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 *
 * 身为一名Java程序员，大家可能都有这样的经历：调用一个方法得到了返回值却不能直接将返回值作为参数去调用别的方法。
 * 我们首先要判断这个返回值是否为null，只有在非空的前提下才能将其作为其他方法的参数。
 *
 * Optional这个容器实际上只能存放一个元素，再对这个元素进行操作
 */
public class OptionalTest {

    /**
     * 为非null的值创建一个Optional。 of方法通过工厂方法创建Optional类。需要注意的是，创建对象时传入的参数不能为null。
     * 如果传入参数为null，则抛出NullPointerException 。
     */
    @Test
    public void testOf() {
        // 调用工厂方法创建Optional实例
        Optional<String> name = Optional.of("Sanaulla");
        // 传入参数为null，抛出NullPointerException.
        Optional<String> someNull = Optional.of(null);
    }

    /**
     * 为指定的值创建一个Optional，如果指定的值为null，则返回一个空的Optional。
     * ofNullable与of方法相似，唯一的区别是可以接受参数为null的情况。
     */
    @Test
    public void testOfNullable() {
        // 下面创建了一个不包含任何值的Optional实例
        // 例如，值为'null'
        Optional<String> empty = Optional.ofNullable(null);
    }

    /**
     * 如果值存在返回true，否则返回false。
     */
    @Test
    public void testIsPresent() {
        Optional<String> name = Optional.of("Sanaulla");
        // isPresent方法用来检查Optional实例中是否包含值
        if (name.isPresent()) {
            // 在Optional实例内调用get()返回已存在的值
            System.out.println(name.get());// 输出Sanaulla
        }
    }

    /**
     * 如果Optional有值则将其返回，否则抛出NoSuchElementException。
     * 上面的示例中，get方法用来得到Optional实例中的值。下面我们看一个抛出NoSuchElementException的例子
     */
    @Test
    public void testGet() {
        Optional<String> empty = Optional.ofNullable(null);
        // 执行下面的代码会输出：No value present
        try {
            // 在空的Optional实例上调用get()，抛出NoSuchElementException
            System.out.println(empty.get());
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * 如果Optional实例有值则为其调用consumer，否则不做处理
     *
     * 要理解ifPresent方法，首先需要了解Consumer类。简答地说，Consumer类包含一个抽象方法。该抽象方法对传入的值进行处理，但没有返回值。Java8支持不用接口直接通过lambda表达式传入参数。
     *
     * 如果Optional实例有值，调用ifPresent()可以接受接口段或lambda表达式。类似下面的代码：
     */
    @Test
    public void testIfPresent() {
        Optional<String> name = Optional.of("Sanaulla");
        // ifPresent方法接受lambda表达式作为参数。
        // lambda表达式对Optional的值调用consumer进行处理。
        name.ifPresent((value) -> {
            System.out.println("The length of the value is: " + value.length());
        });
    }

    /**
     * 如果有值则将其返回，否则返回指定的其它值。
     *
     * 如果Optional实例有值则将其返回，否则返回orElse方法传入的参数。
     */
    @Test
    public void testOrElse() {
        Optional<String> empty = Optional.ofNullable(null);
        Optional<String> name = Optional.of("Sanaulla");
        // 如果值不为null，orElse方法返回Optional实例的值。
        // 如果为null，返回传入的消息。
        // 输出：There is no value present!
        System.out.println(empty.orElse("There is no value present!"));
        // 输出：Sanaulla
        System.out.println(name.orElse("There is some value!"));
    }

    /**
     * 如果有值，则对其执行调用mapping函数得到返回值。如果返回值不为null，则创建包含mapping返回值的Optional作为map方法返回值，否则返回空Optional。
     *
     * map方法用来对Optional实例的值执行一系列操作。通过一组实现了Function接口的lambda表达式传入操作。
     */
    @Test
    public void testMap() {
        Optional<String> name = Optional.of("Sanaulla");
        // map方法执行传入的lambda表达式参数对Optional实例的值进行修改。
        // 为lambda表达式的返回值创建新的Optional实例作为map方法的返回值。
        Optional<String> upperName = name.map((value) -> value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));
    }

    //===================2019-03-27======================================

    /**
     * 实用防空技巧
     */
    @Test
    public void testOptional() {
        List<Integer> list = Lists.newArrayList(1,2,3);
        list = Optional.ofNullable(list).orElse(Lists.newArrayList());
        System.out.println(list);
    }

    @Test
    public void testOptionalAndConsumer(){
        String string = "aw";
        Optional<String> optional = Optional.ofNullable(string);
        optional.ifPresent(s->{
            System.out.println(s.toUpperCase());
        });
        optional.orElseGet(String::new);
        optional.orElseGet(() -> get());
    }

    private String get(){
        return null;
    }
}
