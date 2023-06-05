package com.todo_app.back_end.todo_app_back_end.security.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfg {

        private final JwtAuthFilter jwtAuthFilter;

        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http

                                .csrf(c -> c.disable())

                                .cors(cors -> cors.disable())

                                .authorizeHttpRequests((authz) -> authz

                                                .requestMatchers("/graphql").permitAll()

                                                .anyRequest().authenticated()

                                )

                                .sessionManagement(sm -> sm

                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                                ) // Spring will create a new session for every request => every request should be
                                  // authenticated

                                // .headers(header ->

                                // header.frameOptions().sameOrigin()

                                // )

                                .authenticationProvider(authenticationProvider)

                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();

        }

        @Bean
        public FilterRegistrationBean corsFilter() {

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

                CorsConfiguration config = new CorsConfiguration();

                config.addAllowedOrigin("*");

                config.addAllowedHeader("*");

                config.addAllowedMethod("*");

                source.registerCorsConfiguration("/**", config);

                FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));

                bean.setOrder(0);

                return bean;
        }

}
