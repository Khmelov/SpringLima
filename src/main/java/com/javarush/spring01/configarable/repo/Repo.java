package com.javarush.spring01.configarable.repo;

import com.javarush.spring01.configarable.entity.User;

public interface Repo {

    User getById(Long id);
}
