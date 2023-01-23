package com.eternalgooner.dhis2webapigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security config which specifies which endpoints need authentication.
 * Any future endpoint security requirements could be added here.
 * CSRF & frame options have been set purely for local dev & wouldn't be allowed in production.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**")
                .authenticated()
                .and()
                .authorizeRequests()
                .antMatchers("/api/**")
                .authenticated()
                .and()
                .httpBasic()
                .and().csrf().disable();

        // to allow h2 console to show in browser correctly
        http.headers().frameOptions().disable();
        return http.build();
    }
}
