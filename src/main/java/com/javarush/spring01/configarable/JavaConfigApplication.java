package com.javarush.spring01.configarable;

import com.javarush.spring01.configarable.config.ApplicationConfig;
import com.javarush.spring01.configarable.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class JavaConfigApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        //check beans
        String[] names = applicationContext.getBeanDefinitionNames();
        System.out.println("=============== context =================");
        Arrays.asList(names).forEach(System.out::println);
        System.out.println("=============== context =================");
        //one bean
        UserService userService = applicationContext.getBean(UserService.class);
        System.out.println("bean userService=" + userService);
        //use bean
        System.out.println("user=" + userService.getUser(1L).orElseThrow());
        System.out.println("user=" + userService.getUser(2L).orElseThrow());
    }
}
