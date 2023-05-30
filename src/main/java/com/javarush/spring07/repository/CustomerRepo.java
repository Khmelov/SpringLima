package com.javarush.spring07.repository;

import com.javarush.spring07.entity.Customer;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@SuppressWarnings("NullableProblems")
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query("""
            select c from Customer c
            where c.login like concat(:startingWith, '%')
            and c.login like concat('%', :endingWith)
            """)
    Optional<Customer> findCustomer(@Param("startingWith") String startingWith, @Param("endingWith") String endingWith);


    @Override
    void flush();

    @Override
    <S extends Customer> S saveAndFlush(S entity);

    @Override
    <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    default void deleteInBatch(Iterable<Customer> entities) {
        JpaRepository.super.deleteInBatch(entities);
    }

    @Override
    void deleteAllInBatch(Iterable<Customer> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    Customer getOne(Long aLong);

    @Override
    Customer getById(Long aLong);

    @Override
    Customer getReferenceById(Long aLong);

    @Override
    <S extends Customer> List<S> findAll(Example<S> example);

    @Override
    <S extends Customer> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Customer> List<S> saveAll(Iterable<S> entities);

    @Override
    List<Customer> findAll();

    @Override
    List<Customer> findAllById(Iterable<Long> longs);

    @Override
    <S extends Customer> S save(S entity);

    @Override
    Optional<Customer> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Customer entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends Customer> entities);

    @Override
    void deleteAll();

    @Override
    List<Customer> findAll(Sort sort);

    @Override
    Page<Customer> findAll(Pageable pageable);

    @Override
    <S extends Customer> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Customer> long count(Example<S> example);

    @Override
    <S extends Customer> boolean exists(Example<S> example);

    @Override
    <S extends Customer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
