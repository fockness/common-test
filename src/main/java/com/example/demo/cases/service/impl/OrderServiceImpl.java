package com.example.demo.cases.service.impl;

import com.example.demo.cases.entity.domain.SysUser;
import com.example.demo.cases.service.OrderService;
import com.example.demo.cases.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    @Override
    public void judgeUsers() {
        Optional<List<SysUser>> users = Optional.of(Lists.newArrayList());//userService.getUsers();
        users.ifPresent(u->{
            //有元素就进来
            System.out.println(u);
        });
    }
}
