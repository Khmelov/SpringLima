package com.javarush.spring07.db;

import com.javarush.spring07.entity.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Customer, Long> {

    List<Customer> getOrderedBy(Sort.TypedSort<Long> idSorter);

    @Nullable Customer getById(@Nullable Long aLong);

    Optional<Customer> findById(Long id);

}