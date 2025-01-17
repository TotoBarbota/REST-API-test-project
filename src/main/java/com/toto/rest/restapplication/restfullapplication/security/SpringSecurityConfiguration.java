package com.toto.rest.restapplication.restfullapplication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {



        return http
                .authorizeHttpRequests(
                    auth -> auth
                                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                    .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                .csrf(Customizer. withDefaults())
                .build();
    }
}
