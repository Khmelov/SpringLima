package com.javarush.spring01.configarable.repo;

import com.javarush.spring01.configarable.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Data
public class UserRepo implements Repo {

    @Autowired
    public void setUserMap(Map<Long, User> userMap) {
        this.userMap = userMap;
    }

    private Map<Long, User> userMap;

    @Override
    public User getById(Long id) {
        return userMap.get(id);
    }
}
