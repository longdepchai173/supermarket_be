package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public class NotHavePermissionException extends BaseException{
    private static final String MESSAGE_KEY = "user.not.have.permission";
    public NotHavePermissionException(String userEmail) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), userEmail));
    }
}
