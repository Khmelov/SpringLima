package com.javarush.spring01.xml;

import com.javarush.spring01.xml.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class XmlApplication {
    public static final String APPLICATION_XML = "application.xml";

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(APPLICATION_XML);
        //check beans
        String[] names = applicationContext.getBeanDefinitionNames();
        System.out.println("=============== context =================");
        Arrays.asList(names).forEach(System.out::println);
        System.out.println("=============== context =================");
        //one bean
        UserService userService = applicationContext.getBean(UserService.class);
        System.out.println("bean userService=" + userService);
        //use bean
        System.out.println("user=" + userService.getUser(1L));
    }
}
