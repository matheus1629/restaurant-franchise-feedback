package com.restaurant.feedbackscollector.exception;

public class MessageSendFailedException extends RuntimeException {
    public MessageSendFailedException(String message) {
        super(message);
    }
}