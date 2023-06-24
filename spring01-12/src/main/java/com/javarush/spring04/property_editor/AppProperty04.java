package com.javarush.spring04.property_editor;

import com.javarush.spring04.property_editor.entity.CreditCard;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppProperty04 {

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        CreditCard creditCard = context.getBean(CreditCard.class);
        System.out.println("create " + creditCard);
    }

}
