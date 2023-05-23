package com.javarush.spring07.repository;

import java.util.Optional;

public interface Repo<E> {

    Optional<E> findById(Long id);

    void save(E entity);
}
