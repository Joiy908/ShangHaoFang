package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity //@EnableWebSecurity是开启SpringSecurity的默认行为
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("");
//    }

    /**
     * 必须指定加密方式，上下加密方式要一致
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许iframe嵌套显示
        http.headers().frameOptions().disable();

        //登录设置
        //允许匿名用户访问的路径
        http.authorizeRequests()
            .antMatchers("/static/**","/login").permitAll()  //允许匿名用户访问的路径
            .anyRequest().authenticated(); // 其它页面全部需要验证


        //用户未登录时，访问任何需要权限的资源都转跳到该路径，
        // 即登录页面，此时登陆成功后会继续跳转到第一次访问的资源页面（相当于被过滤了一下）
        http.formLogin().loginPage("/login")
                .defaultSuccessUrl("/"); //登录认证成功后默认转跳的路径，意思时admin登录后也跳转到/user

        http.logout()
                .logoutUrl("/logout")   //退出登陆的路径，指定spring security拦截的注销url,退出功能是security提供的
                .logoutSuccessUrl("/login"); //用户退出后要被重定向的url

        http.csrf().disable();//关闭跨域请求伪造功能
    }
}