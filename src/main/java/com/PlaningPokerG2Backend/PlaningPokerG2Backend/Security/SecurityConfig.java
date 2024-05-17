package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // @Bean
    // SecurityFilterChain configure(HttpSecurity http) throws Exception {

    //     http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

    //     return http.build();
    // }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
       http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());



        return http.build();
    }
}
