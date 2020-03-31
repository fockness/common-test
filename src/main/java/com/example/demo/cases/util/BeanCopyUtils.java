package com.example.demo.cases.util;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BeanCopyUtils extends BeanUtils {

    public static void main(String[] args) {
        List<A> list = Lists.newArrayList(new A("1"));
        copyListProperties(list, B::new, (a, b)->{
            System.out.println("1");
        });
    }

    /**
     * 集合数据的拷贝
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }

    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @param callBack: 回调函数
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }
}

@Data
class A{
    private String name;

    public A(){}

    public A(String name){
        this.name = name;
    }
}

@Data
class B{
    private String name;

    public B(){}

    public B(String name){
        this.name = name;
    }
}


