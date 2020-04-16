package com.example.demo.cases.service.impl;

import com.example.demo.cases.domain.SysUser;
import com.example.demo.cases.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Optional<List<SysUser>> getUsers() {
        Optional<List<SysUser>> users = Optional.of(Lists.newArrayList(new SysUser()));
        return users.filter(validateUsers("1"));
    }


    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }
}
