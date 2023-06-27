package com.javarush.spring13;

import com.javarush.spring13.entity.User;
import com.javarush.spring13.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class SpringLesson13 {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringLesson13.class, args);
        UserService userService = context.getBean(UserService.class);
        Mono<User> mono = userService.get(1L);
        System.out.println(mono.block());
    }
}
