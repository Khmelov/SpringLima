package com.javarush.spring13.controller;

import com.javarush.spring13.entity.User;
import com.javarush.spring13.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.InputMismatchException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(UserRestController.ENDPOINT)
public class UserRestController {

    public static final String ENDPOINT = "/restapi/v1/users";
    private final UserService userService;

    //READ
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<User> findAll() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public Mono<User> get(@PathVariable Long id) {
        return userService.get(id);
    }

    //WRITING
    //CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody User user) {
            return userService.save(user);
    }

    //UPDATE
    @PutMapping("/{id}") //or @PatchMapping (if only part data update)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<User> update(@PathVariable Long id, @RequestBody User user) {
        if (id.equals(user.getId()))
            return userService.save(user);
        else {
            log.error("update with incorrect id={}", id);
            return Mono.error(new IllegalStateException("update with incorrect id"));
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
            return userService.deleteById(id);
    }

}
