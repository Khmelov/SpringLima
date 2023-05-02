package com.javarush.spring01.annotation.repo;

import com.javarush.spring01.annotation.entity.User;

public interface Repo {

    User getById(Long id);
}
