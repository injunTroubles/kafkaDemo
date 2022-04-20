package com.example.demo.controller;

import com.example.demo.domain.MessageEnvelope;
import com.example.demo.domain.MessageName;
import com.example.demo.domain.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final KafkaTemplate<String, Object> template;

    @Autowired
    public MessageController(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    @PostMapping("/messages")
    void sendMessages(@RequestParam(defaultValue = "1") int repeatTimes) {
        MessageEnvelope messageEnvelope = new MessageEnvelope(MessageType.COMMAND, MessageName.PROCESS_MESSAGE, "hello world");

        for (int i = 0; i < repeatTimes; i++) {
            template.send("demo-command", messageEnvelope);
        }
    }
}
