package com.javarush.spring17kafka.withoutboot;


import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Winter {

    private static final Map<Class<?>, Object> container = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> type) {
        return (T) container.computeIfAbsent(type, t -> create(t.getConstructors()[0]));
    }

    private static Object create(Constructor<?> constructor) {
        try {
            return constructor.newInstance(
                    Arrays.stream(constructor.getParameterTypes())
                            .map(Winter::getBean)
                            .toArray());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}

