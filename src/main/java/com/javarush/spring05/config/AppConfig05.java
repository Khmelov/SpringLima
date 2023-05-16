package com.javarush.spring05.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.javarush.spring05")
@PropertySource("classpath:application-05.properties")
public class AppConfig05 {

}
