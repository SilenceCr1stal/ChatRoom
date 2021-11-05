package com.example.chatroom;

public class Message {
    public String username;
    public String message;

    public Message() {
        username = RegistrationPage.username;
        message = "";
    }

    public Message(String username, String message) {
        this.username = username;
        this.message = message;
    }
}
