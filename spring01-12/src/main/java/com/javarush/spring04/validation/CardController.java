package com.javarush.spring04.validation;

import com.javarush.spring04.validation.entity.Card;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Set;

@Slf4j
@Controller
public class CardController {

    private final Validator validator;

    @Getter
    private final Map<String, String> demoSpringLanguage;


    @Autowired
    public CardController(
            @Qualifier("demoQualifierValidator")
            Validator validator,

            @Value("#{${cnnData : {'db.url': '${db.url}','db.user': '${db.user}','db.password': '${db.password}'}}}")
            Map<String, String> demoSpringLanguage
    ) {
        this.validator = validator;
        this.demoSpringLanguage = demoSpringLanguage;
    }

    public boolean manualCheck(@Valid Card card) {
        Set<ConstraintViolation<Card>> violations = validator.validate(card);
        for (ConstraintViolation<Card> violation : violations) {
            System.out.println(violation.getMessage());
        }
        return violations.isEmpty();
    }
}


