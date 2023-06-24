package com.javarush.spring13.repository;

import com.javarush.spring13.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface Repo extends ReactiveCrudRepository<User, Long> {


}
