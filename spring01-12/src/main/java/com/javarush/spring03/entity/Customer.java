package com.javarush.spring03.entity;

import lombok.Data;

@Data
public class Customer {

    private Long id;

    private String login;

    private String password;

}
