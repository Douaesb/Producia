package com.prod.producia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Profile("dev")
    @Configuration
    public static class DevSecurityConfig {
        @Bean
        public HttpSecurity httpSecurity(HttpSecurity http) throws Exception {
            http.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authz -> authz
                            .anyRequest().permitAll());
            return http;
        }
    }

    @Profile("prod")
    @Configuration
    public static class ProdSecurityConfig {

        private final javax.sql.DataSource dataSource;
        private final PasswordEncoder passwordEncoder;

        public ProdSecurityConfig(javax.sql.DataSource dataSource, PasswordEncoder passwordEncoder) {
            this.dataSource = dataSource;
            this.passwordEncoder = passwordEncoder;
        }


        @Bean
        public HttpSecurity httpSecurity(HttpSecurity http) throws Exception {

            http.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authz -> authz
                            .requestMatchers("/api/admin/**").hasRole("ADMIN")
                            .requestMatchers("/api/user/**").hasRole("USER")
                            .anyRequest().authenticated());

            return http;
        }

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {

            AuthenticationManagerBuilder authenticationManagerBuilder =
                    http.getSharedObject(AuthenticationManagerBuilder.class);

            authenticationManagerBuilder
                    .jdbcAuthentication()
                    .dataSource(dataSource)
                    .usersByUsernameQuery("SELECT username, password, active FROM users WHERE username = ?")
                    .passwordEncoder(passwordEncoder);

            return authenticationManagerBuilder.build();
        }
    }
}
