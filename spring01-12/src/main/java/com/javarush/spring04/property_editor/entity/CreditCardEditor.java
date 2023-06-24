package com.javarush.spring04.property_editor.entity;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * Стандартная инфраструктура JavaBeans автоматически обнаружит классы PropertyEditor,
 * если они находятся в том же пакете, что и класс, с которым они работают.
 * Кроме того, они должны иметь то же имя, что и этот класс, плюс суффикс Editor.
 */
@Component
public class CreditCardEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        CreditCard creditCard = (CreditCard) getValue();
        
        return creditCard == null ? "" : creditCard.getRawCardNumber();
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasText(text)) {
            setValue(null);
        } else {
            CreditCard creditCard = new CreditCard();
            creditCard.setRawCardNumber(text);
            
            String cardNo = text.replaceAll("-", "");
            if (cardNo.length() != 16)
                throw new IllegalArgumentException(
                  "Credit card format should be xxxx-xxxx-xxxx-xxxx");

            try {
                creditCard.setBankIdNo( Integer.valueOf(cardNo.substring(0, 6)) );
                creditCard.setAccountNo( Integer.valueOf(
                  cardNo.substring(6, cardNo.length() - 1)) );
                creditCard.setCheckCode( Integer.valueOf(
                  cardNo.substring(cardNo.length() - 1)) );
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException(nfe);
            }
            
            setValue(creditCard);
        }
    }
}