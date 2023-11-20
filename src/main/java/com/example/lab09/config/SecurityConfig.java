package com.example.lab09.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Không sử dụng password encoding, chỉ sử dụng cho mục đích demo
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/account/register", "/api/account/login").permitAll()
                .antMatchers("/api/products").authenticated()
                .antMatchers("/api/orders").permitAll()
                .and()
                .formLogin()
                .loginPage("/api/account/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable(); // Tắt CSRF để đơn giản hóa cho mục đích demo
    }
}
