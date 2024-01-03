package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public class CompartmentHasDiffProduct extends BaseException{
    private static final String MESSAGE_KEY = "compartment.has.difference.product";
    public CompartmentHasDiffProduct(String compartmentId, String productId) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), compartmentId, productId));
    }
}
