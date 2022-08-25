package com.tastyfoodwebapplication.config;

import com.tastyfoodwebapplication.enums.UserRole;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private AccessDeniedHandlerConfig accessDeniedHandlerConfig;

    @Autowired
    public SecurityConfig(AccessDeniedHandlerConfig accessDeniedHandlerConfig) {
        this.accessDeniedHandlerConfig = accessDeniedHandlerConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/cart/**", "/order/**").authenticated()
            .anyRequest()
                .permitAll()
                .and()
            .formLogin()
                .loginPage("/")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandlerConfig)
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}