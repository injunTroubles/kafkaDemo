package com.example.demo.domain;


public class MessageEnvelope {

    public MessageEnvelope() {
    }

    public MessageEnvelope(MessageType type, MessageName name, String correlationId, Person payload) {
        this.type = type;
        this.name = name;
        this.correlationId = correlationId;
        this.payload = payload;
    }

    MessageType type;
    MessageName name;
    String correlationId;
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

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public Person getPayload() {
        return payload;
    }

    public void setPayload(Person payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "MessageEnvelope{" +
                "type=" + type +
                ", name=" + name +
                ", correlationId='" + correlationId + '\'' +
                ", payload=" + payload +
                '}';
    }
}