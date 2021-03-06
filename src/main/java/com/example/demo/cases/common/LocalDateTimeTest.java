package com.example.demo.cases.common;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalDateTimeTest {


    @Test
    public void testLocalTime(){
        LocalDateTime time = LocalDateTime.now();
        System.out.println("toString:" + time.toString()); //字符串表示
        System.out.println("toLocalTime:" + time.toLocalTime()); //获取时间(LocalTime)
        System.out.println("toLocalDate:" + time.toLocalDate()); //获取日期(LocalDate)
        System.out.println("getDayOfMonth:" + time.getDayOfMonth()); //获取当前时间月份的第几天
        System.out.println("getDayOfWeek:" + time.getDayOfWeek());  //获取当前周的第几天
        System.out.println("getDayOfYear:" + time.getDayOfYear());  //获取当前时间在该年属于第几天
        System.out.println("getHour:" + time.getHour());
        System.out.println("getMinute:" + time.getMinute());
        System.out.println("getMonthValue:" + time.getMonthValue());
        System.out.println("getMonth:" + time.getMonth());
        System.out.println("-----------------------------------");

        //LocalDateTime对象转String
        System.out.println(time.format(DateTimeFormatter.ISO_DATE));
    }

    @Test
    public void testMethods(){
       /* LocalDateTime now = LocalDateTime.now();
        //减半小时
        System.out.println(now.minusMinutes(30L));
        System.out.println(now.plusMinutes(30L));*/

        String regex = "[\u4e00-\u9fa5]";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher("你asd好");
        System.out.println(match.find());
    }
}
