package com.example.demo.cases.common;

import lombok.Data;
import org.junit.Test;

import java.util.function.Supplier;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-5-10 16:23
 * 4
 * supplier也是是用来创建对象的，但是不同于传统的创建对象语法：new，惰性求值
 */
public class SupplierTest {

    @Test
    public void testGet(){
        //创建Supplier容器，声明为TestSupplier类型，此时并不会调用对象的构造方法，即不会创建对象
        Supplier<User> supplier = User::new;
        Supplier<User> supplier1 = () -> new User("aa");
        //调用get()方法，此时会调用对象的构造方法，即获得到真正对象;每次get都会调用构造方法，即获取的对象不同
        User user = supplier.get();


    }

    @Data
    static class User{
        private String name;
        private Integer age;

        public User(){}

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
