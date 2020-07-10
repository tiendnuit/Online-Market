package edu.miu.cs545.group5.onlinemarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] lists = {"/",
                "/css/**", "/js/**", "/images/**",
                "/h2-console/**"};

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(lists).permitAll()
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();
    }
}
