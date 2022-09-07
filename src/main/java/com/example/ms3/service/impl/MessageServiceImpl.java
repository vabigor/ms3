package com.example.ms3.service.impl;

import com.example.ms3.model.Message;
import com.example.ms3.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService {

    private final RestTemplate restTemplate;

    @Value("${ms1.url}")
    private String URL;

    public MessageServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void send(Message message) {
        message.setMs3Timestamp(new Date());
        restTemplate.postForObject(URL, message, Message.class);
    }
}
