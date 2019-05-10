package com.example.demo.cases.common;

import lombok.Data;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-5-10 15:18
 * 4
 */
public class ConsumerTest {

    @Test
    public void testAccept(){
        /*1
        List<User> result = Lists.newArrayList();
        Consumer<User> consumer = user -> {
            if(user.name.equals("aa")){
                result.add(user);
            }
        };
        List<User> list = Lists.newArrayList(new User("aa"), new User("bb"));
        list.forEach(consumer);
        System.out.println(result);*/


        /*2
        User u = new User();
        Consumer<User> consumer = user -> user.setName("aa");
        consumer.accept(u);
        System.out.println(u.getName());*/

        User u = new User();
        Consumer<User> consumerA = user -> user.setName("aa");
        Consumer<User> consumerB = user -> user.setAge(1);
        consumerA.andThen(consumerB).accept(u);
        System.out.println(u);
    }

    /**
     * 综合使用Predicate和Consumer
     */
    @Test
    public void testPredicateAndConsumer(){
        User u = new User("aa");
        updateUser(u, user -> user.getName().equals("aa"), user -> user.setAge(20));
        System.out.println(u);
    }

    private void updateUser(User user, Predicate<User> predicate, Consumer<User> consumer){
        if(predicate.test(user)){
            consumer.accept(user);
        }
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


