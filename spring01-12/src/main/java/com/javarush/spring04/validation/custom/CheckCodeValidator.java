package com.javarush.spring04.validation.custom;

import com.javarush.spring04.validation.entity.Card;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class CheckCodeValidator implements ConstraintValidator<CheckCode, Card> {
    @Override
    public boolean isValid(Card card, ConstraintValidatorContext constraintValidatorContext) {
        return card.getCheckCode() == Arrays
                .stream(card.getRawCardNumber().split("[^0-9]"))
                .flatMap(s -> Arrays.stream(s.split("")))
                .filter(a -> !a.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
