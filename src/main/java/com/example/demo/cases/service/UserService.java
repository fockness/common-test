package com.example.demo.cases.service;

import com.example.demo.cases.domain.SysUser;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface UserService {

    /**
     * 参数校验可以放在顶层
     * @param id
     * @return
     */
    default Predicate<List<SysUser>> validateUsers(String id){
        Predicate<List<SysUser>> min = p -> p.get(0).getName().length() > 8;
        Predicate<List<SysUser>> max = p -> p.get(0).getName().length() < 32;
        return min.and(max);
    }

    default Optional<SysUser> getUser(String id){
        return Optional.empty();
    }

    Optional<List<SysUser>> getUsers();
}
