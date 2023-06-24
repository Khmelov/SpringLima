package com.javarush.spring01.xml.service;

import com.javarush.spring01.xml.entity.User;
import com.javarush.spring01.xml.repo.UserRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> getUser(Long userId) {
        User byId = userRepository.getById(userId);
        return Optional.of(byId);
    }

    @Override
    public String toString() {
        return "UserService";
    }
}
