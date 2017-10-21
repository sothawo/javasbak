/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.javasbak;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
@Component
public class KafkaConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConnector.class);

    private final List<KafkaConnectorListener> listeners = new ArrayList<>();
    private final KafkaTemplate<String, String> kafka;

    public KafkaConnector(@SuppressWarnings("SpringJavaAutowiringInspection") KafkaTemplate<String, String> kafka) {
        this.kafka = kafka;

    }

    public void addListener(KafkaConnectorListener listener) {
        listeners.add(listener);
    }

    public void removeListener(KafkaConnectorListener listener) {
        listeners.remove(listener);
    }

    public void send(String user, String mesage) {
        LOGGER.info("user " + user + "sending message \"" + mesage + '"');
        kafka.send("javasbak-chat", user, mesage);
    }

    @KafkaListener(topics = {"javasbak-chat"})
    public void receive(ConsumerRecord<String, String> consumerRecord) {
        String key = consumerRecord.key();
        if (null == key) {
            key = "???";
        }
        String value = consumerRecord.value();
        if (null == value) {
            value = "???";
        }
        LOGGER.info("got kafka record with key \"" + key + "\" and value \"" + value + '"');
        for(KafkaConnectorListener listener :listeners) {
            listener.chatMessage(key, value);
        };
    }
}
