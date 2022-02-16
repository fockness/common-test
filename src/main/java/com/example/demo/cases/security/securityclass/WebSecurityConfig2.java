package com.example.demo.cases.security.securityclass;

import liquibase.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberUserDetailService memberUserDetailService;

    /**
     * 添加授权账户（账户与权限配置）
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 例1、最简单的创建一个授权账户
        auth.inMemoryAuthentication().withUser("admin1").password("admin").authorities("/");
        **/
        /**
         * 例2、路径授权
         * 权限不足的情况下抛403可以配合统一异常处理 、或者配TomcatServletWebServerFactory来处理403异常转而请求其她接口来处理

        auth.inMemoryAuthentication().withUser("admin1").password("admin").authorities("addMember");
         */
        /**
         * 例3、从数据库中动态读取用户权限
         */
        auth.userDetailsService(memberUserDetailService).passwordEncoder(new PasswordEncoder() {
            /**
             * 对密码加密
             * @param rawPassword
             * @return
             */
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.computeMD5((String)rawPassword);
            }

            /**
             * rawPassword是前端传入的未加密的面膜
             * encodedPassword是数据库查出来的加密后的密码
             * @param rawPassword
             * @param encodedPassword
             * @return
             */
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String rawPass = MD5Util.computeMD5((String)rawPassword);
                return rawPass.equals(encodedPassword);
            }
        });
    }

    /**
     * 权限与路径配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 例1、配置httpbasic协议认证
        http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();
         http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().formLogin();默认
         **/

        /**
         * 例2、路径授权

        http.authorizeRequests().antMatchers("/addMember").hasAnyAuthority("addMember")
                .antMatchers("/**").fullyAuthenticated().and().formLogin();
         */

        /**
         * 例3、定义登陆页面
         */
        http.authorizeRequests().antMatchers("/addMember").hasAnyAuthority("addMember")
                .antMatchers("/login").permitAll()//要是没有配置这个，就是递归查找该路径，重定向过多异常
                .antMatchers("/**").fullyAuthenticated().and().formLogin().loginPage("/login").and().csrf().disable();

    }

    /**
     * 例1、密码需要加密
     * @return
     */
    @Bean
    public static NoOpPasswordEncoder passwordEncoder(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    
}