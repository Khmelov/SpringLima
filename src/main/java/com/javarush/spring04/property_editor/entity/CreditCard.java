package com.javarush.spring04.property_editor.entity;

import lombok.Data;

@Data
public class CreditCard {
    private String rawCardNumber;
    private Integer bankIdNo;
    private Integer accountNo;
    private Integer checkCode;
}