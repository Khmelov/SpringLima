package com.javarush.spring05.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@EnableAspectJAutoProxy
public class Locator {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceAnnotation() {
    }

    @Pointcut("within(com..*Service)")
    public void isServiceByName() {
    }

    @Pointcut("within(com..repository.impl.*)")
    public void isRepoByPackage() {
    }

    @Pointcut("isServiceAnnotation() || isServiceByName()")
    public void isService() {
    }

    @Pointcut("execution(public * com..*Service.get(*))")
    public void isMethodByName() {
    }






}
