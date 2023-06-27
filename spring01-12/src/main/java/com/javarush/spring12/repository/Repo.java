package com.javarush.spring12.repository;

import com.javarush.spring12.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<User, Long> {


}
