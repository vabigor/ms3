package com.example.ms3.consumer;

import com.example.ms3.model.Message;
import com.example.ms3.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class KafkaConsumer {

    private final MessageService service;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public KafkaConsumer(MessageService service) {
        this.service = service;
    }

    @KafkaListener(topics = "message", containerFactory = "kafkaListenerContainerFactory")
    @Transactional
    public void orderListener(@Payload List<Message> messages){
        logger.info("MS3 received message");
        messages.forEach(service::send);
    }
}
