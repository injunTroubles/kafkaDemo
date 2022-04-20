package com.example.demo.domain;


public class MessageEnvelope {

    public MessageEnvelope() {
    }

    public MessageEnvelope(MessageType type, MessageName name, Person payload) {
        this.type = type;
        this.name = name;
        this.payload = payload;
    }

    MessageType type;
    MessageName name;
    Person payload;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public MessageName getName() {
        return name;
    }

    public void setName(MessageName name) {
        this.name = name;
    }

    public Person getPayload() {
        return payload;
    }

    public void setPayload(Person payload) {
        this.payload = payload;
    }
}