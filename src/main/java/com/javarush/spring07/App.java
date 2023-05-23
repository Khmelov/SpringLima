package com.javarush.spring07;

import com.javarush.spring07.entity.Customer;
import com.javarush.spring07.service.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        var context = SpringApplication.run(App.class, args);
        try (context) {
            long id = 1L;
            CustomerService customerService = context.getBean(CustomerService.class);
            Customer customer = customerService.get(id).orElseThrow();
            System.out.println(customerService.get(id).orElseThrow());
            customer.setPassword("test12345");
            customerService.update(customer);
            System.out.println(customerService.get(id).orElseThrow());
            customer.setPassword("456");
            customerService.update(customer);
            System.out.println(customerService.get(id).orElseThrow());
        }
    }
}
