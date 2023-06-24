package com.javarush.spring13.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.spring13.entity.Role;
import com.javarush.spring13.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRestControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void findAll() {
        webTestClient.get().uri(UserRestController.ENDPOINT)
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(User.class);
    }

    @Test
    void get() {
        webTestClient.get().uri(UserRestController.ENDPOINT + "/1")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(User.class);
    }

    @Test
    void createAndUpdateAndDelete() throws JsonProcessingException {
        User user = User.builder()
                .login("test_user")
                .password("test_password")
                .role(Role.USER)
                .build();

        byte[] responseBody = webTestClient.post().uri(UserRestController.ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.login").isEqualTo(user.getLogin())
                .jsonPath("$.password").isEqualTo(user.getPassword())
                .returnResult()
                .getResponseBody();
        User userFromDb = objectMapper.readValue(new String(responseBody), User.class);
        Assertions.assertTrue(userFromDb.getId() > 0);

        userFromDb.setPassword("newPassword");
        webTestClient.put().uri(UserRestController.ENDPOINT + "/" + userFromDb.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(userFromDb), User.class)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(User.class);

        webTestClient.delete().uri(UserRestController.ENDPOINT + "/" + userFromDb.getId())
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().is2xxSuccessful();
    }
}