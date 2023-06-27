package com.javarush.spring12.integration;

import com.javarush.spring12.entity.Role;
import com.javarush.spring12.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRestTemplateIT {

    public static final String ENDPOINT = "/api/rest/v1/users/";
    public static final String ENDPOINT_ID = "/api/rest/v1/users/{id}";
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGet() {
        User user = testRestTemplate.getForObject(ENDPOINT_ID, User.class,1);
        assertEquals("Ivan", user.getLogin());
    }

    @Test
    void testPost() {
        User user = User.builder().login("hello").password("world").role(Role.GUEST).build();
        ResponseEntity<User> response = testRestTemplate.postForEntity(ENDPOINT, user, User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        User body = response.getBody();
        assertNotNull(body);
        assertThat(body.getId()).isGreaterThan(0L);
        assertThat(body.getLogin()).isEqualTo(user.getLogin());
        assertThat(body.getPassword()).isEqualTo(user.getPassword());
        testRestTemplate.delete(ENDPOINT_ID, body.getId(), body);
    }

    @Test
    void testPut() {
        User user = User.builder().login("hello").password("world").role(Role.GUEST).build();
        ResponseEntity<User> response = testRestTemplate.postForEntity(ENDPOINT, user, User.class);
        user=response.getBody();
        assertNotNull(user);
        String hello = "Hello";
        String world = "World";
        user.setLogin(hello);
        user.setPassword(world);
        testRestTemplate.put(ENDPOINT_ID, user, response.getBody().getId());
        User updatedUser = testRestTemplate.getForObject(ENDPOINT + response.getBody().getId(), User.class);
        assertThat(updatedUser.getLogin()).isEqualTo(hello);
        assertThat(updatedUser.getPassword()).isEqualTo(world);
        testRestTemplate.delete(ENDPOINT_ID, updatedUser.getId(), updatedUser);
    }

    @Test
    void testDelete() {
        User user = User.builder().login("hello").password("world").role(Role.GUEST).build();
        ResponseEntity<User> response = testRestTemplate.postForEntity(ENDPOINT, user, User.class);
        assertNotNull(response.getBody());
        testRestTemplate.delete(ENDPOINT_ID, response.getBody().getId(), response.getBody());
        ResponseEntity<User> deletedUser = testRestTemplate.getForEntity(ENDPOINT_ID, User.class, response.getBody().getId());
        assertThat(deletedUser.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
