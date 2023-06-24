package com.javarush.spring04.validation.entity;

import com.javarush.spring04.validation.custom.CheckCode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@CheckCode
public class Card {

    @Size(min = 16, max = 32)
    private String rawCardNumber;

    @Positive
    private Integer bankIdNo;

    @NotNull
    private Integer accountNo;

    @Positive
    @NotNull
    private Integer checkCode;

}