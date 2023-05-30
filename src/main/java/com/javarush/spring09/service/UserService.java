package com.javarush.spring09.service;

import com.javarush.spring09.entity.User;
import com.javarush.spring09.repository.Repo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final Repo repo;

    public Optional<User> get(Long id) {
        return repo.findById(id);
    }

    public List<User> findAll() {
        return repo.findAll();
    }

    @Transactional
    public User save(User user) {
        return repo.saveAndFlush(user);
    }

    @Transactional
    public void delete(User user) {
        repo.delete(user);
    }

}
