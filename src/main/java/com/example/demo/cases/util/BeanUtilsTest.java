package com.example.demo.cases.util;

import org.junit.Test;

public class BeanUtilsTest {

    @Test
    public void testPopulate() throws Exception{
        Dto dto = new Dto();
//        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
//        map.put("name", "1");
//        BeanUtils.populate(dto, map);
        System.out.println(dto.getName());
    }
}
