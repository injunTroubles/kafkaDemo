package com.example.demo.controller;

import com.example.demo.domain.MessageEnvelope;
import com.example.demo.domain.MessageName;
import com.example.demo.domain.MessageType;
import com.example.demo.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    void sendCommand(@RequestBody Person person, @RequestParam(defaultValue = "1") int repeatTimes) {

        MessageEnvelope messageEnvelope = new MessageEnvelope(MessageType.COMMAND, MessageName.PROCESS_PERSON, person);

        for (int i = 0; i < repeatTimes; i++) {
            template.send("demo-command", messageEnvelope);
        }
    }
}
