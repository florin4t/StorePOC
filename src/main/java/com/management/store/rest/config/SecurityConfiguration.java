package com.management.store.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Value("${run.with.security:false}")
    private String enableSecurity;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //using conditional on bean and profiles did not work so I did it programmatically
        if (Boolean.parseBoolean(enableSecurity)) {
            http
                    .authorizeHttpRequests((authz) -> authz
                            .antMatchers("/error", "/info")
                            .permitAll()
                            .anyRequest()
                            .authenticated()
                    )
                    .csrf().disable()
                    .httpBasic(withDefaults());
        } else {
            http
                    .authorizeHttpRequests((authz) -> authz
                            .antMatchers("/**")
                            .permitAll()
                            .anyRequest()
                            .permitAll()
                    )
                    .csrf().disable();

        }
        return http.build();
    }
}

