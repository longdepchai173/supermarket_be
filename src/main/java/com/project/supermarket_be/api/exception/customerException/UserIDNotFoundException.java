package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public class UserIDNotFoundException extends NotFoundException {
    private static final String MESSAGE_KEY = "user.id.not.found.error";
    public UserIDNotFoundException(String userID) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), userID));
    }
}
