package com.javarush.spring15.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = LoggingRepositoryProperties.PREFIX)
public class LoggingRepositoryProperties {

    public static final String PREFIX = "logging.repository";
    /**
     * set log level ERROR, WARN, INFO, DEBUG, TRACE
     * default INFO
     */
    @Value("${logging.repository.level?:INFO}")
    private String level;

    /**
     * set "ON" for start logging
     * default "OFF"
     **/
    @Value("${logging.repository.state?:OFF}")
    private String state;

}
