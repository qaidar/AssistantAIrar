package com.kodelib.chatai.models;

public class Message {
    String message;
    boolean fromSender;

    public Message(String message, boolean fromSender) {
        this.message = message;
        this.fromSender = fromSender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFromSender() {
        return fromSender;
    }

    public void setFromSender(boolean fromSender) {
        this.fromSender = fromSender;
    }
}
