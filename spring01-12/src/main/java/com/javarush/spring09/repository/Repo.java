package com.javarush.spring09.repository;

import com.javarush.spring09.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<User, Long> {


}
