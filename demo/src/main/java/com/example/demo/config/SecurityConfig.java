package com.example.demo.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailService userDetailService;


    public SecurityConfig(
            CustomUserDetailService userDetailService
    ){
        this.userDetailService = userDetailService;
    }


    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/login",
                                "/register"
                        ).permitAll()
                        .requestMatchers("/enroll/**").hasRole("STUDENT")
                        .requestMatchers("/mycourses").hasRole("STUDENT")
                        .anyRequest()
                        .authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                )

                .authenticationProvider(
                        authProvider()
                );


        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {

        DaoAuthenticationProvider auth =
                new DaoAuthenticationProvider(
                        userDetailService   // ✅ truyền vào constructor
                );

        auth.setPasswordEncoder(
                passwordEncoder()
        );

        return auth;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){

        return NoOpPasswordEncoder.getInstance();

    }

}