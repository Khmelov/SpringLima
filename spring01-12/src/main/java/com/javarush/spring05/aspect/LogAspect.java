package com.javarush.spring05.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(1)
public class LogAspect {
    @Before("Locator.isService()")
    public void getMethodInAnnotatedService(JoinPoint point) {
        log.warn("Before Service annotated {} ", point.toShortString());
    }

    @After("Locator.isServiceByName()")
    public void getMethodInServiceByName(JoinPoint point) {
        log.warn("After Service by name {} ", point.toShortString());
    }
}
