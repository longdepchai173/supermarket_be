package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public class PasswordErrorException extends NotFoundException{
    private final static String MESSAGE_KEY = "user.password.error";
    public PasswordErrorException(String userEmail) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), userEmail));
    }

}
