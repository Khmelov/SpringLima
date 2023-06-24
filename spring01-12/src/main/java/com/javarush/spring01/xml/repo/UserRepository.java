package com.javarush.spring01.xml.repo;

import com.javarush.spring01.xml.entity.User;
import lombok.Data;

import java.util.Map;

@Data
public class UserRepository implements Repository {

    private Map<Long, User> userMap;

    @Override
    public User getById(Long id) {
        return userMap.get(id);
    }
}
