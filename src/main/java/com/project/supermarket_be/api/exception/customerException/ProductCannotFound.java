package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public class ProductCannotFound extends BaseException{
    private final static String MESSAGE_KEY = "product.cannot.found";
    public ProductCannotFound(String productId) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), productId));
    }
}
