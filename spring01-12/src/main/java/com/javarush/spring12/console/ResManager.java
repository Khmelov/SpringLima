package com.javarush.spring12.console;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResManager {
    INSTANCE;

    private ResourceBundle resourceBundle;

    public void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("console", locale);
    }

    ResManager() {
        setLocale(Locale.ENGLISH);
    }

    public String get(String  key){
        return resourceBundle.getString(key);
    }
}
