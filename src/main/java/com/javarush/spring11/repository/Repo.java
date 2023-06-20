package com.javarush.spring11.repository;

import com.javarush.spring11.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<User, Long> {


}
