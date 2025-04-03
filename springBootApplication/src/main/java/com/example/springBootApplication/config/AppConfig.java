package com.example.springBootApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class AppConfig {

    @Bean(name = "furkan")
    public Logger logger() {
        return Logger.getLogger("FurkanLogger");
    }
}

