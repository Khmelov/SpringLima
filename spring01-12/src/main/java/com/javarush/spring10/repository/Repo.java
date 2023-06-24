package com.javarush.spring10.repository;

import com.javarush.spring10.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<User, Long> {


}
