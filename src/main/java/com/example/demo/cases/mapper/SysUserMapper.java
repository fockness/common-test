package com.example.demo.cases.mapper;

import com.example.demo.cases.entity.domain.SysUser;
import org.apache.ibatis.annotations.Insert;

//@Mapper
public interface SysUserMapper {

    @Insert("INSERT INTO sys_user(name, age)VALUES(#{name},#{age})")
    void insert(SysUser sysUser);
}
