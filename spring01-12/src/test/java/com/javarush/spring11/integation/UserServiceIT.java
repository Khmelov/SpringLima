package com.javarush.spring11.integation;

import com.javarush.spring11.entity.Role;
import com.javarush.spring11.entity.User;
import com.javarush.spring11.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MyTestContext
public class UserServiceIT {

    public static final int COUNT_USER_IN_DB = 3;
    private final UserService userService;

    public static final long USER_ID = 1L;
    private User inUser;
    private User expectedUser;

    public UserServiceIT(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    void setup() {
        inUser = User.builder()
                .login("test_login")
                .password("test_password")
                .role(Role.GUEST)
                .build();
        expectedUser = User.builder()
                .id(USER_ID)
                .login("admin")
                .password("456")
                .role(Role.ADMIN)
                .build();
    }

    @Test
    void get() {
        User actualUser = userService.get(USER_ID).orElseThrow();
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getLogin(), actualUser.getLogin());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getRole(), actualUser.getRole());
    }

    @Test
    void findAll() {
        List<User> users = userService.findAll();
        assertEquals(COUNT_USER_IN_DB,users.size());
    }

    @Test
    void save() {
        User actualUser = userService.save(inUser);
        assertTrue(actualUser.getId()>0);
        assertEquals(inUser.getLogin(), actualUser.getLogin());
        assertEquals(inUser.getPassword(), actualUser.getPassword());
        assertEquals(inUser.getRole(), actualUser.getRole());
    }

    @Test
    void delete() {
        User user = userService.save(inUser);
        userService.delete(user);
        int size = userService.findAll().size();
        assertEquals(COUNT_USER_IN_DB, size);
    }

    @Test
    void deleteById() {
        User user = userService.save(inUser);
        userService.deleteById(user.getId());
        int size = userService.findAll().size();
        assertEquals(COUNT_USER_IN_DB, size);
    }
}