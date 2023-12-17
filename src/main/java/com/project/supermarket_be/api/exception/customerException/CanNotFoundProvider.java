package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public class CanNotFoundProvider extends BaseException{
    private static final String MESSAGE_KEY = "provider.cannot.found";
    public CanNotFoundProvider(String providerId) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), providerId));
    }
}
