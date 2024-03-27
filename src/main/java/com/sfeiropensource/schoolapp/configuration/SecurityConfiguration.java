package com.sfeiropensource.schoolapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class SecurityConfiguration {

    @Value("${spring.security.enabled:#{true}}")
    private String securityEnabled;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // If security is enabled.
        if (Boolean.parseBoolean(securityEnabled)) {
            // Set-up has a resource server with spring security.
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .cors(cors -> cors.configure(http))
                    .authorizeHttpRequests(authorize -> authorize
                            // Authorize swagger freely
                            .requestMatchers(
                                    antMatcher("/v3/api-docs/**"),
                                    antMatcher("/swagger-ui/**")
                            ).permitAll()
                            // And other requests authenticated.
                            .anyRequest().permitAll()
                    )
                    .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

        } else {
            // Else, authorize all request.
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .cors(cors -> cors.configure(http))
                    .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
        }
        return http.build();
    }
}