package com.example.demo.consumer;

import com.example.demo.domain.MessageEnvelope;
import com.example.demo.domain.MessageName;
import com.example.demo.domain.MessageType;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private final KafkaTemplate<String, Object> template;

    @Autowired
    public KafkaConsumer(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    @KafkaListener(topics = {"demo-command", "demo-domain"}, groupId = "demo", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, String> consumerRecord, @Payload MessageEnvelope messageEnvelope) {
        logger.info("Received: Payload: {} | Record: {}", messageEnvelope, consumerRecord.toString());

        /*
            DO SOME WORK
            DATABASE OPERATIONS, API INTERACTIONS, ETC...
        */

        MessageEnvelope outboundMessage = new MessageEnvelope(MessageType.EVENT, MessageName.PERSON_PROCESSED, messageEnvelope.getCorrelationId(), messageEnvelope.getPayload());

        /*
            ONCE WORK IS COMPLETE CAN SEND RESULT TO DOMAIN AND/OR PUBLIC TOPIC(S) IN CASE OTHER OPERATIONS BEYOND
            THE SCOPE OF THIS METHOD NEED TO BE CARRIED OUT

            SINCE THIS CONSUMER IS LISTENING TO BOTH THE COMMAND AND DOMAIN TOPICS, THE SWITCH STATEMENT BELOW IS USED TO
            ELIMINATE INFINITE MESSAGE CONSUMPTION.

            THIS IS WHERE SERVICE METHOD DELEGATION CAN BE PLUGGED IN. COULD LEVERAGE THE MESSAGE NAME TO DETERMINE HOW
            TO PROCESS SPECIFIC MESSAGES
         */

        switch (consumerRecord.topic()) {
            case "demo-command":
                template.send("demo-domain", outboundMessage);
                break;
            default:
                template.send("demo-public", outboundMessage);
        }
    }
}
