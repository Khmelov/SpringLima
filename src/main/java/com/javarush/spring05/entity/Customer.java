package com.javarush.spring05.entity;

import lombok.Data;

@Data
public class Customer {

    private Long id;

    private String login;

    private String password;

}
