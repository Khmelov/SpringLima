package com.javarush.spring10.console;

import java.util.Locale;

public class Runner {
    public static void main(String[] args) {
        ResourceManager translator =  ResourceManager.INSTANCE;
        Locale locale = new Locale("ru","RU");
        translator.setLocale(locale);
        System.out.println(translator.get(Message.GREETING));
        System.out.println(translator.get(Message.QUESTION));
        System.out.println(translator.get(User.FIST_NAME));
        System.out.println(translator.get(User.LAST_NAME));

    }
}
