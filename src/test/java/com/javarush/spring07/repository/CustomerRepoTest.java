package com.javarush.spring07.repository;

import com.javarush.spring07.entity.Customer;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@Context
@AllArgsConstructor
class CustomerRepoTest {

    private final CustomerRepo customerRepo;


    @Test
    void findCustomerByLoginStartingWithAndLoginEndingWith() {
        Customer customer = customerRepo
                .findCustomer("i", "n")
                .orElseThrow();
        assertEquals("ivan", customer.getLogin());
    }

    @Test
    void findAll() {
        Customer customer = customerRepo
                .findCustomer("i", "n")
                .orElseThrow();

        List<Customer> all = customerRepo.findAll();
        all.forEach(System.out::println);
        assertTrue(all.size() > 0);
        assertTrue(all.contains(customer));

    }

    @Test
    void findAndPageable() {
        Sort.TypedSort<Customer> sort = Sort.sort(Customer.class);
        Sort orders = sort.by(Customer::getLogin)
                .and(sort.by(Customer::getId))
                .descending();
        Pageable pageable = PageRequest.of(0, 3, orders);
        Page<Customer> page = null;
        do {
            page = customerRepo.findAll(pageable);
            pageable = pageable.next();
            page.forEach(System.out::println);
            System.out.printf("Page %d, pages %d, count %d%n"
                    , page.getNumber()
                    , page.getTotalPages()
                    , page.getTotalElements());
        } while (page.hasNext());

    }

    @Test
    void findAndSort() {
        Sort.TypedSort<Customer> sort = Sort.sort(Customer.class);
        Sort orders = sort.by(Customer::getLogin)
                .and(sort.by(Customer::getId))
                .descending();
        List<Customer> allSort = customerRepo.findAll(orders);
        allSort.forEach(System.out::println);
    }
}