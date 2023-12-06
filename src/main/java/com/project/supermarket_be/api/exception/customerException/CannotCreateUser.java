package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public  class CannotCreateUser extends NotFoundException {
    private static final String MESSAGE_KEY = "user.cannot.create";
    public CannotCreateUser(String userId) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), userId));
    }
}

