package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public class UserNotFoundException extends NotFoundException{
    private static final String MESSAGE_KEY = "user.not.found.error";
    public UserNotFoundException(String userEmail) {
            super(MessageFormat.format(getMessage(MESSAGE_KEY), userEmail));
    }
}
