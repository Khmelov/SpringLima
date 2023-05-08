package com.javarush.spring01.configurable.repo;

import com.javarush.spring01.configurable.entity.User;

public interface Repo {

    User getById(Long id);
}
