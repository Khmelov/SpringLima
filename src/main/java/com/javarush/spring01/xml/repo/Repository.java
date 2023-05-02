package com.javarush.spring01.xml.repo;

import com.javarush.spring01.xml.entity.User;

public interface Repository {

    User getById(Long id);
}
