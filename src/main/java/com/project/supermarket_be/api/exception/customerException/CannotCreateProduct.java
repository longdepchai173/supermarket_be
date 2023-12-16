package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public class CannotCreateProduct extends BaseException{
    private static final String MESSAGE_KEY = "can.not.create.product";
    public CannotCreateProduct(String message) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), message));
    }
}
