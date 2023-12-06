package com.project.supermarket_be.api.exception.customerException;

import java.util.ResourceBundle;

public abstract class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    protected static String getMessage(String messageKey) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages");
        return bundle.getString(messageKey);
    }
}

