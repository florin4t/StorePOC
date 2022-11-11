package com.management.store.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@Profile("secure")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class EnableSecurityConfig {
}
