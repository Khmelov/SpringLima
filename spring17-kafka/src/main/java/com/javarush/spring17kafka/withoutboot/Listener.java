package com.javarush.spring17kafka.withoutboot;

import org.springframework.kafka.annotation.KafkaListener;

class Listener {

    @KafkaListener(id = "listen1", topics = "topic1")
    public void listen1(String in) {
        System.out.println(in);
    }

}
