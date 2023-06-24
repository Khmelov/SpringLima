package com.javarush.spring01.annotation;

import com.javarush.spring01.annotation.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class AnnotationApplication {
    public static final String BASE_PACKAGE = "com.javarush.spring01.annotation";

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BASE_PACKAGE);
        //check beans
        String[] names = applicationContext.getBeanDefinitionNames();
        System.out.println("=============== context =================");
        Arrays.asList(names).forEach(System.out::println);
        System.out.println("=============== context =================");
        //one bean
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("bean userService=" + userService);
        //use bean
        System.out.println("user=" + userService.getUser(1L).orElseThrow());
        System.out.println("user=" + userService.getUser(2L).orElseThrow());
    }
}
