package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public class CannotFoundWarehouseInvoice extends BaseException{
    private static final String MESSAGE_KEY = "cannot.found.warehouse";
    public CannotFoundWarehouseInvoice(String warehouseId) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), warehouseId));
    }
}
