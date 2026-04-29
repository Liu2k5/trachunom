package com.liu.trachunom.config;

import com.vaadin.flow.spring.security.RequestUtil;
import com.vaadin.flow.spring.security.VaadinSavedRequestAwareAuthenticationSuccessHandler;
import com.vaadin.flow.spring.security.VaadinSecurityConfigurer;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.nio.charset.StandardCharsets;

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
                                "/login/**"
                        ).permitAll()
                        .requestMatchers(
                                "/search",
                                "/entity/**"
                        ).permitAll()
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")
                        .requestMatchers(requestUtil::isFrameworkInternalRequest).permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(new VaadinSavedRequestAwareAuthenticationSuccessHandler())
                        .failureHandler(loginFailureHandler())
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

    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
//            boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"))
//                    || (request.getHeader("Accept") != null && request.getHeader("Accept").contains("application/json"));

            String reason;
            if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
                reason = "incorrect_email_or_password";
            } else if (exception instanceof DisabledException) {
                reason = "account_disabled";
            } else {
                reason = "unknown_error";
            }

//            if (isAjax) {
                // trả lỗi cho AJAX: status 401 + JSON body
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                String json = String.format("{\"error\":\"%s\"}", reason);
                response.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
                response.getOutputStream().flush();
//            } else {
//                // thông thường: redirect về login với param để trang login hiển thị thông báo
//                response.sendRedirect("/login?" + reason);
//            }
        };
    }
}
