package com.example.ms3.config.desiriallizer;

import com.example.ms3.model.Message;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Десериализатор
 */
public class KafkaDeserializer implements Deserializer<Message> {

    private static final ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(KafkaDeserializer.class);

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        mapper = objectMapper;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public Message deserialize(String s, byte[] bytes) {
        try {
            return mapper.readValue(bytes, Message.class);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void close() {
    }

}
