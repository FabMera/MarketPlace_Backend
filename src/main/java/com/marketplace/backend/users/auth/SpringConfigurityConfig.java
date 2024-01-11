package com.marketplace.backend.users.auth;

import com.marketplace.backend.users.helpers.URLFrontEnd;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SpringConfigurityConfig {

    private final JwtAuthFilter jwtAuthorizationFilter;

    public SpringConfigurityConfig(JwtAuthFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors((Customizer.withDefaults()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(URLFrontEnd.URL_FRONTEND_SESSION,"/users/","/users/register","/users/login", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
                                .permitAll()
                                //.requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole(Roles.USUARIO_PRINCIPAL, Roles.ADMIN)
                                .requestMatchers(HttpMethod.DELETE, "users/miperfil/{id}/delete").hasAnyRole(Roles.USUARIO_PRINCIPAL, Roles.ADMIN)
                                .requestMatchers(HttpMethod.POST, "/users/**").hasAnyRole(Roles.ADMIN, Roles.USUARIO_PRINCIPAL)
                                .requestMatchers(HttpMethod.POST, "/users/login").hasAnyRole(Roles.ADMIN, Roles.USUARIO_PRINCIPAL)
                                .anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList(URLFrontEnd.URL_FRONTEND_SESSION));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
