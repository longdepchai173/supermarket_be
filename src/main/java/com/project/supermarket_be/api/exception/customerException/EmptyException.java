package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class EmptyException extends BaseException{
    private static final String MESSAGE_KEY = "empty.exception";
    public EmptyException(String message) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), message));
    }
}