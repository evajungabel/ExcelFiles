package com.excel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> {
                    authz.requestMatchers("/", "/index", "/upload-excel-file", "/search-excel-file",
                            "/css/**",
                            "/js/**",
                            "/assets/**",
                            "/api/excelupload",
                            "/api/excelsearch").permitAll();
                    authz.requestMatchers("/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/v2/api-docs").permitAll();
                    authz.anyRequest().authenticated();
                });
        return http.build();
    }

}
