package com.javarush.spring04.validation;

import com.javarush.spring04.validation.entity.Card;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppValidator04 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        var card = Card.builder()
                .rawCardNumber("1234-1234-1111-0012")
                .bankIdNo(123412)
                .accountNo(341111001)
                .checkCode(26)
                .build();
        CardController cardController = context.getBean(CardController.class);
        System.out.println("spel map: " + cardController.getDemoSpringLanguage());
        boolean ok = cardController.manualCheck(card);
        if (ok) {
            System.out.println("all correct");
        } else {
            System.out.println("fount invalid fields");
        }
    }
}
