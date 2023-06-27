package com.javarush.spring15.logger;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Aspect
@Slf4j
@EnableAspectJAutoProxy
public class LocatorAspect {

    @Pointcut("isRepositoryInterface() || isRepoByPackage()")
    public void isRepository() {
    }


    @Pointcut("this(org.springframework.data.repository.Repository) ||" +
              "this(org.springframework.data.repository.reactive.ReactiveCrudRepository)")
    private void isRepositoryInterface() {
    }


    @Pointcut("within(com..repository.impl.*)")
    private void isRepoByPackage() {
    }

    @PostConstruct
    void init(){
        log.info("================================");
    }

}
