package com.javarush.spring05.repository;

public interface Repo<E> {

    E getById(Long id);

    void update(E entity);
}
