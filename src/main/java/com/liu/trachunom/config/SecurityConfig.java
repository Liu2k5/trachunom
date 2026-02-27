package com.liu.trachunom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                "/connect/**",
                                "/HILLA/**",
                                "/login"
                        )
                        // Bỏ qua CSRF cho tất cả request có param v-r (Vaadin internal)
                        .ignoringRequestMatchers(request ->
                                request.getParameter("v-r") != null
                        )
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/VAADIN/**",
                                "/vaadinServlet/**",
                                "/frontend/**",
                                "/frontend-es5/**",
                                "/frontend-es6/**",
                                "/HILLA/**",
                                "/connect/**",
                                "/images/**",
                                "/icons/**",
                                "/favicon.ico",
                                "/manifest.webmanifest",
                                "/sw.js",
                                "/offline-stub.html",
                                "/login",
                                "/login/**"
                        ).permitAll()
                        // Permit Vaadin UIDL/heartbeat/push requests dựa trên param v-r
                        .requestMatchers(request ->
                                request.getParameter("v-r") != null
                        ).permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            String vR = request.getParameter("v-r");
                            String accept = request.getHeader("Accept");
                            String contentType = request.getHeader("Content-Type");

                            // Vaadin UIDL/heartbeat/push requests → trả 401, không redirect
                            boolean isVaadinRequest = vR != null
                                    || (contentType != null && contentType.contains("application/json"))
                                    || (accept != null && accept.contains("application/json"));

                            if (isVaadinRequest) {
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.getWriter().write("{\"error\":\"Unauthorized\"}");
                            } else {
                                // Browser request bình thường → redirect về login
                                response.sendRedirect("/login");
                            }
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            String vR = request.getParameter("v-r");
                            String contentType = request.getHeader("Content-Type");

                            boolean isVaadinRequest = vR != null
                                    || (contentType != null && contentType.contains("application/json"));

                            if (isVaadinRequest) {
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            } else {
                                response.sendRedirect("/login");
                            }
                        })
                );

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(
            @Value("${app.username}") String username,
            @Value("${app.password}") String password) {

        String encodedPassword = password.startsWith("$2a$") || password.startsWith("$2b$")
                ? "{bcrypt}" + password
                : "{noop}" + password;

        return new InMemoryUserDetailsManager(
                User.builder()
                        .username(username)
                        .password(encodedPassword)
                        .authorities("ADMIN")
                        .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}