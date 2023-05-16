package com.javarush.spring04.property_editor;

import com.javarush.spring04.property_editor.entity.CreditCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan()
@PropertySource("classpath:application-04.properties")
public class Config {

    @Bean
    @Scope("prototype")
    CreditCard creditCard(@Value("${card:1234-1234-1111-0019}") CreditCard creditCard) {
        return creditCard;
    }

}
