package com.javarush.spring01.annotation.repo;

import com.javarush.spring01.annotation.entity.User;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Data
public class UserRepo implements Repo {

    private Map<Long, User> userMap = Map.of(
            1L, new User("John", "john@gmail.com"),
            2L, new User("Smith", "smith@gmail.com")
    );

    @Override
    public User getById(Long id) {
        return userMap.get(id);
    }
}
