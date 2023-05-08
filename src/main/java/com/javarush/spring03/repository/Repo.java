package com.javarush.spring03.repository;

public interface Repo<E> {

    E getById(Long id);

    void update(E entity);
}
