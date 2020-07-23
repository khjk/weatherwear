package com.kitri.weatherwear.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
<<<<<<< HEAD
                    .antMatchers("/","/user/login","/css/**","/images/**","/js/**","/api/v1/**","/users/save-wear","users/eval-wear","/users/**").permitAll()
=======
                    .antMatchers("/","/user/login","/css/**","/images/**","/js/**","/api/v1/**","/users/**").permitAll()
>>>>>>> a41aff1f47a289dcecf3e54118ac14c508e11599
                    //.antMatchers("/api/v1/**").hasRole("USER")
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }

}
