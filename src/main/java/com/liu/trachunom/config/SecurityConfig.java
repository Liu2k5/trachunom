package com.liu.trachunom.config;

import com.vaadin.flow.spring.security.RequestUtil;
import com.vaadin.flow.spring.security.VaadinSavedRequestAwareAuthenticationSuccessHandler;
import com.vaadin.flow.spring.security.VaadinSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements UserDetailsService {

    @Autowired
    private RequestUtil requestUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .with(VaadinSecurityConfigurer.vaadin(),
                        vaadinSecurityConfigurer -> vaadinSecurityConfigurer.loginView("/login"))
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(requestUtil::isFrameworkInternalRequest)
                        .ignoringRequestMatchers("/login")
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/VAADIN/**",
                                "/vaadinServlet/**",
                                "/frontend/**",
                                "/frontend-es5/**",
                                "/frontend-es6/**",
                                "/HILLA/**",
                                "/images/**",
                                "/icons/**",
                                "/favicon.ico",
                                "/manifest.webmanifest",
                                "/sw.js",
                                "/offline-stub.html",
                                "/login",
                                "/login/**",
                                "/admin/**"
                        ).permitAll()
                        .requestMatchers(requestUtil::isFrameworkInternalRequest).permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(new VaadinSavedRequestAwareAuthenticationSuccessHandler())
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(
//            @Value("${app.username}") String username,
//            @Value("${app.password}") String password) {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username(username)
//                        .password("{noop}" + password)
//                        .authorities("ADMIN")
//                        .build()
//        );
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Value("${app.username}")
    private String appUsername;

    @Value("${app.password}")
    private String appPassword;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return sampleUser();
    }

    private UserDetails sampleUser() {
        return User.builder()
                .username(appUsername)
                .password("{noop}" + appPassword)
                .roles("ADMIN")
                .build();
    }
}
