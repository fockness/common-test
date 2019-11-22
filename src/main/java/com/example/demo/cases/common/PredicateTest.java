package com.example.demo.cases.common;

import com.google.common.collect.Lists;
import lombok.Data;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 2 * @Author: shibin
 * 3 * @Date: 2019-5-9 17:36
 * 4
 */
public class PredicateTest {

    @Test
    public void testPredicate() {
        List<User> users = Lists.newArrayList(new User(10), new User(20), new User(5));
        users = UserPredicate.filterUser(users, UserPredicate.moreThan18());//或者p -> p.getAge() > 18也可以
        System.out.println(users);

        //Predicate<User> predicate = p -> p.getAge() >= 10;
        //将多个predicate组合在一起
        users = UserPredicate.filterUser(users, UserPredicate.p1().and(UserPredicate.p2()).and(UserPredicate.p3));
    }

    @Test
    public void testPredicateAndConsumer(){
        UserPredicate2.testAndSet(()->new User(1), u->u.getAge()>1, u->u.setAge(2));
    }
}

@Data
class User{
    private Integer age;

    public User(Integer age) {
        super();
        this.age = age;
    }
}

/**
 * 断言单独写一个类中，使用的时候静态引入
 * @author shibin
 *
 */
class UserPredicate{

    public static Predicate<User> moreThan18(){
        return p->p.getAge() >= 10;
    }

    public static Predicate<User> p1(){
        return p->p.getAge()>1;
    }

    public static Predicate<User> p2(){
        return p->p.getAge()>2;
    }

    public static Predicate<User> p3 = p->p.getAge()>3;

    /**
     * 相当于自定义过滤器
     * @param users
     * @param predicate
     * @return
     */
    public static List<User> filterUser(List<User> users, Predicate<User> predicate){
        return users.stream().filter(predicate).collect(Collectors.<User>toList());
    }
}

class UserPredicate2{

    /**
     * 泛用性接口，首先根据predicate进行boolean判断，true之后使用consumer进行对象内部赋值操作
     * @param supplier
     * @param predicate
     * @param consumer
     */
    public static void testAndSet(Supplier<User> supplier, Predicate<User> predicate, Consumer<User> consumer){
        User user = supplier.get();
        if(predicate.test(user)){
            consumer.accept(user);
        }
    }
}