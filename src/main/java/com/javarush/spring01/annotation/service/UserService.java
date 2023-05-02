package com.javarush.spring01.annotation.service;

import com.javarush.spring01.annotation.entity.User;
import com.javarush.spring01.annotation.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepo userRepository;

    public Optional<User> getUser(Long userId) {
        User byId = userRepository.getById(userId);
        return Optional.of(byId);
    }

    @Override
    public String toString() {
        return "UserService";
    }
}
