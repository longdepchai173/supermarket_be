package com.project.supermarket_be.api.exception.customerException;

public class TokenIsEmptyException extends BaseException{
    private static final String MESSAGE_KEY = "token.is.empty";
    public TokenIsEmptyException(String message) {
        super(getMessage(MESSAGE_KEY));
    }
}
