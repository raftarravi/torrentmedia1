package com.torrentmedia.torrentmedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Public URLs (before login)
                        .requestMatchers("/", "/about", "/services", "/register", "/login").permitAll()

                        // Protected URLs (only after login)
                        .requestMatchers("/profile/**").authenticated()

                        // Any other requests are also public
                        .anyRequest().permitAll()
                )
                // Enable login form
                .formLogin(form -> form
                        .loginPage("/login")          // Your custom login page
                        .defaultSuccessUrl("/profile", true) // Redirect after login
                        .permitAll()
                )
                // Enable logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }
}

