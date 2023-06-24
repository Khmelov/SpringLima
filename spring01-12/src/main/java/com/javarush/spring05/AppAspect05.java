package com.javarush.spring05;

import com.javarush.spring05.config.AppConfig05;
import com.javarush.spring05.entity.Customer;
import com.javarush.spring05.service.CustomerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppAspect05 {


    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig05.class);
        try (context){
            long id = 1L;
            CustomerService customerService = context.getBean(CustomerService.class);
            Customer customer = customerService.get(id);
            System.out.println(customerService.get(id));
            customer.setPassword("test12345");
            customerService.update(customer);
            System.out.println(customerService.get(id));
            customer.setPassword("456");
            customerService.update(customer);
            System.out.println(customerService.get(id));
        }
    }
}
