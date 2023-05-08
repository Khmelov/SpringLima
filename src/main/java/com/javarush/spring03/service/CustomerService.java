package com.javarush.spring03.service;

import com.javarush.spring03.entity.Customer;
import com.javarush.spring03.processor.Benchmark;
import com.javarush.spring03.processor.Tx;
import com.javarush.spring03.repository.Repo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Benchmark
public class CustomerService {

    private Repo<Customer> customerRepo;

    @Autowired
    public void setCustomerRepo(Repo<Customer> customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Tx
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
