package com.javarush.spring15.logger;

import com.javarush.spring15.config.LoggingRepositoryProperties;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.event.Level;
import org.slf4j.spi.LoggingEventBuilder;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Aspect
public class LogAspect {

    @Autowired
    private LoggingRepositoryProperties loggingRepositoryProperties;

    private LoggingEventBuilder out;

    @Around("com.javarush.spring15.logger.LocatorAspect.isRepository()")
    public Object getMethodInAnnotatedService(ProceedingJoinPoint point) {
        String methodRepository = point.toShortString();
        try {
            out.log("Before call {} ", methodRepository);
            Object result = point.proceed();
            out.log("After call {} ", methodRepository);
            return result;
        } catch (Throwable e) {
            log.error("ERROR call {} ", methodRepository);
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    void init() {
        Level level = Level.valueOf(loggingRepositoryProperties.getLevel().toUpperCase());
        out = log.makeLoggingEventBuilder(level);
    }

}
