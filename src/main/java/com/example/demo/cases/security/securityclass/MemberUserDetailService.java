package com.example.demo.cases.security.securityclass;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //1、根据该用户名称查询在数据库中是否存在
        UserEntity userEntity = new UserEntity();//从数据库里查出来的用户
        //2、需要联表查询对应用户的权限
        //3、将该权限添加到security
        List<GrantedAuthority> authorities = Lists.newArrayList();
        List<Permission> list = Lists.newArrayList();//从数据库里查出来的权限
        list.forEach(i->{
            authorities.add(new SimpleGrantedAuthority(i.getPermTag()));
        });
        userEntity.setAuthorities(authorities);
        return userEntity;
    }
}

@Data
class Permission{
    private String permTag;
}
