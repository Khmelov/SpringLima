package com.javarush.spring01.configurable.config;

import com.javarush.spring01.configurable.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ComponentScan("com.javarush.spring01.configurable")
public class ApplicationConfig {

    @Bean
    Map<Long, User> userMap() {
        return Map.of(
                1L, new User("John", "john@gmail.com"),
                2L, new User("Smith", "smith@gmail.com")
        );
    }

}
