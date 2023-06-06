package com.javarush.spring10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@PropertySource("classpath:application-10.properties")
public class App_i18n {
    public static void main(String[] args) {
        SpringApplication.run(App_i18n.class, args);
    }
}
