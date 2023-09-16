package com.commerce.shop.security;

import com.commerce.shop.security.filter.AuthenticationFilter;
import com.commerce.shop.security.filter.ExceptionHandelFilter;
import com.commerce.shop.security.filter.JWTAuthorizationFilter;
import com.commerce.shop.security.manager.CustomAuthenticationManager;
import com.commerce.shop.security.user.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private CustomAuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);
        authenticationFilter.setFilterProcessesUrl("/auth/login");


        JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(userDetailService);
        http
                .cors(cors->cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->request
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/category/**", "/product/**", "/search/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/register/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/logout/**").permitAll()
                        .requestMatchers("/user/**").hasRole("EMPLOYEE")
                        .requestMatchers("/cart/**").hasRole("EMPLOYEE")
                        .anyRequest().authenticated())
                .addFilterBefore(new ExceptionHandelFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(jwtAuthorizationFilter, AuthenticationFilter.class);

        return http.build();
    }

}
