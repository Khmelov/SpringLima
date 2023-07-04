package com.javarush.spring17kafka.withoutboot;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public class Sender {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        context.getBean(Sender.class).send("============= test =============", 42L);
    }

    private final KafkaTemplate<Long, String> template;

    public Sender(KafkaTemplate<Long, String> template) {
        this.template = template;
    }

    public void send(String toSend, long key) {
        this.template.send("topic1", key, toSend);
    }

}

