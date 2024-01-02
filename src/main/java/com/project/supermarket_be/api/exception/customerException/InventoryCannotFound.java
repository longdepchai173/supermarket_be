package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public class InventoryCannotFound extends BaseException{
    private static final String MESSAGE_KEY = "inventory.cannot.found";
    public InventoryCannotFound(String inventoryId) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), inventoryId));
    }
}
