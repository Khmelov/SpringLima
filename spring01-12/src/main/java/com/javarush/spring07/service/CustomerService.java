package com.javarush.spring07.service;

import com.javarush.spring07.entity.Customer;
import com.javarush.spring07.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;

    public Optional<Customer> get(Long id) {
        return customerRepo.findById(id);
    }

    public void update(Customer customer) {
        customerRepo.save(customer);
    }

}
