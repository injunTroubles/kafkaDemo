package com.example.demo.domain;


public class MessageEnvelope {

    public MessageEnvelope() {
    }

    public MessageEnvelope(MessageType type, MessageName name, String payload) {
        this.type = type;
        this.name = name;
        this.payload = payload;
    }

    MessageType type;
    MessageName name;
    /*
        FOR SIMPLICITY, THE PAYLOAD IS DEFINED AS A STRING.  THIS COULD BE DEFINED AS A GENERIC OBJECT SO IT CAN TRANSPORT
        MORE COMPLEX STRUCTURES. TYPICALLY, THIS ACCOMPLISHED USING JsonNode or Object data types.
     */
    String payload;

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

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}