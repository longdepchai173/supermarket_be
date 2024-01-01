package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public class ShelfCodeAlreadyExists extends BaseException{
    private final static String MESSAGE_KEY = "shelf.code.already.exists";
    public ShelfCodeAlreadyExists(String shelfCode) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), shelfCode));;
    }
}
