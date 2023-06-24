package com.javarush.spring05.service;

import com.javarush.spring05.entity.Customer;
import com.javarush.spring05.repository.Repo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private Repo<Customer> customerRepo;

    @Autowired
    public void setCustomerRepo(Repo<Customer> customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer get(Long id) {
        return customerRepo.getById(id);
    }

    public void update(Customer customer) {
        customerRepo.update(customer);
    }

    @PostConstruct
    void init(){
        System.out.println("init CustomerService");
    }

    @PreDestroy
    void destroy(){
        System.out.println("destroy CustomerService");
    }

}
