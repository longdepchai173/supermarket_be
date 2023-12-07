package com.project.supermarket_be.api.exception.customerException;

import java.util.ResourceBundle;

public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }

    protected static String getMessage(String messageKey) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages");
        return bundle.getString(messageKey);
    }
}