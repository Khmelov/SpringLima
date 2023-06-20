package com.javarush.spring11.service;

import com.javarush.spring11.entity.Role;
import com.javarush.spring11.entity.User;
import com.javarush.spring11.repository.Repo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    Repo repo;

    @InjectMocks
    UserService userService;


    public static final long USER_ID = 1L;
    public static final long USER_DB = 100L;
    private User inUser;
    private User outUser;

    @BeforeEach
    void setup() {
        inUser = User.builder()
                .id(USER_ID)
                .login("mock_login")
                .password("mock_password")
                .role(Role.GUEST)
                .build();
        outUser = User.builder()
                .id(USER_DB)
                .login("mock_login")
                .password("mock_password")
                .role(Role.GUEST)
                .build();

    }

    @Test
    void get() {
        //given
        doAnswer(invocation -> Optional.of(outUser))
                .when(repo)
                .findById(USER_ID);
        //when
        User user = userService.get(USER_ID).orElseThrow();
        //then
        assertEquals(outUser.getId(), user.getId());
        assertEquals(outUser.getLogin(), user.getLogin());
        assertEquals(outUser.getPassword(), user.getPassword());
        assertEquals(outUser.getRole(), user.getRole());
        verify(repo,times(1)).findById(USER_ID);
        verifyNoMoreInteractions(repo);
    }

    @Test
    void findAll() {
        //given
        Sort sort=Sort.sort(User.class).by(User::getId);
        doAnswer(invocation -> List.of(outUser,outUser))
                .when(repo)
                .findAll(sort);
        //when
        List<User> users = userService.findAll();
        //then
        assertEquals(2, users.size());
        verify(repo,times(1)).findAll(sort);
        verifyNoMoreInteractions(repo);
    }

    @Test
    void save() {
        //given
        doAnswer(invocation -> outUser)
                .when(repo)
                .saveAndFlush(inUser);
        //when
        User user = userService.save(inUser);
        //then
        assertEquals(outUser.getId(), user.getId());
        assertEquals(outUser.getLogin(), user.getLogin());
        assertEquals(outUser.getPassword(), user.getPassword());
        assertEquals(outUser.getRole(), user.getRole());
        verify(repo,times(1)).saveAndFlush(inUser);
        verifyNoMoreInteractions(repo);
    }

    @Test
    void delete() {
        //given
        doNothing()
                .when(repo)
                .delete(inUser);
        //when
        userService.delete(inUser);
        //then
        verify(repo,times(1)).delete(inUser);
        verifyNoMoreInteractions(repo);
    }

    @Test
    void deleteById() {
        //given
        doNothing()
                .when(repo)
                .deleteById(USER_ID);
        //when
        userService.deleteById(USER_ID);
        //then
        verify(repo,times(1)).deleteById(USER_ID);
        verifyNoMoreInteractions(repo);
    }
}