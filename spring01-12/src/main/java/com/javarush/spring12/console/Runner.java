package com.javarush.spring12.console;

import java.util.Locale;

public class Runner {
    public static void main(String[] args) {
        ResManager resManager = ResManager.INSTANCE;
        resManager.setLocale(new Locale("ru"));
        System.out.println(resManager.get(Message.GREETING));
        System.out.println(resManager.get(Message.QUESTION));
        System.out.println(resManager.get(User.FIRST_NAME));
        System.out.println(resManager.get(User.LAST_NAME));
    }
}
