package com.example.demo.cases.common;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 此模块用于测试将密码中各个需要校验的部分进行拆分和组合
 */
//@SuppressWarnings("unchecked")
public class PredicateForPassword {

    @Test
    public void testPasswordApart(){
        //由spring进行管理@Bean
        Predicate3<User> predicate3 = new Predicate3<>();
        User user = new User(10);
        predicate3.buildTarget(user).check(UserMessage.FORGET, PasswordPredicate.lessThan28())
                                          .check(UserMessage.LEAVE, PasswordPredicate.moreThan18());

        predicate3.buildTarget(user).batchCheck(UserMessage.FORGET, PasswordPredicate.lessThan28()
                                                                    , PasswordPredicate.moreThan18())
                                          .check(UserMessage.LEAVE, PasswordPredicate.lessThan28());
    }

}

class PasswordPredicate{

    public static Predicate<User> moreThan18(){
        return p->p.getAge() >= 18;
    }

    public static Predicate<User> lessThan28(){
        return p->p.getAge() <= 28;
    }
}


class Predicate3<T>{

    private static final String PARAMETER_LACK = "required parameter is lack";

    private T target = null;

    public Predicate3<T> buildTarget(T target){
        this.target = target;
        return this;
    }

    public Boolean check(Predicate<T> predicate){
        requireNonNull(PARAMETER_LACK, target);
        return predicate.test(target);
    }

    /**
     * userPredicate.buildTarget(new User()).check(UserMessage.FORGET, predicate1)
     *                                .check(UserMessage.FORGET2, predicate2);
     * @param predicate
     * @return
     */
    public Predicate3<T> check(ErrorMessage<String, String> e, Predicate<T> predicate) {
        return batchCheck(e, predicate);
    }

    /**
     * userPredicate.buildTarget(new User()).batchCheck(UserMessage.FORGET, predicate1, predicate2...)
     * @param e
     * @param predicate
     * @return
     */
    public Predicate3<T> batchCheck(ErrorMessage<String, String> e, Predicate<T>... predicate) {
        requireNonNull(PARAMETER_LACK, e, predicate);
        for(Predicate p : predicate){
            if(!check(p)){
                throw new RuntimeException(e.getValue());
            }
        }
        return this;
    }

    /**
     * userPredicate.buildTarget(new User()).check(Validation.class, UserMessage.FORGET, predicate1)
     *                                .check(WowjoyException.class, UserMessage.FORGET, predicate2);
     * @param clazz
     * @param predicate
     * @param errorMessage
     * @return
     */
    public <E extends RuntimeException> Predicate3<T> check(Class<E> clazz
                                , ErrorMessage<String, String> errorMessage, Predicate<T> predicate){
        return batchCheck(clazz, errorMessage, predicate);
    }

    /**
     * userPredicate.buildTarget(new User()).batchCheck(Validation.class, UserMessage.FORGET, predicate1, predicate2);
     * @param clazz
     * @param errorMessage
     * @param predicate
     * @param <E>
     * @return
     */
    public <E extends RuntimeException> Predicate3<T> batchCheck(Class<E> clazz
            , ErrorMessage<String, String> errorMessage, Predicate<T>... predicate){
        requireNonNull(PARAMETER_LACK, clazz, errorMessage, predicate);
        try{
            for(Predicate p : predicate){
                if(!check(p)){
                    Constructor<E> constructor = clazz
                            .getConstructor(String.class);
                    E e = constructor.newInstance(errorMessage.getValue());
                    throw e;
                }
            }
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException r){
            r.printStackTrace();
        }
        return this;
    }

    public static <T> boolean isAnyNull(T... objs) {
        if(Objects.isNull(objs)){
            return true;
        }
        for(T t : objs){
            if(Objects.isNull(t)){
                return true;
            }
        }
        return false;
    }

    public static <T> void requireNonNull(String message, T... objs) {
        if (isAnyNull(objs)){
            throw new NullPointerException(message);
        }
    }
}

enum UserMessage implements ErrorMessage<String, String>{
    FORGET("1", "aaa"),
    LEAVE("2", "bbb"),;
    String type;
    String value;

    UserMessage(String type, String value){
        this.type = type;
        this.value = value;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getValue() {
        return value;
    }
}

interface ErrorMessage<V, T>{
    T getType();
    V getValue();
}
