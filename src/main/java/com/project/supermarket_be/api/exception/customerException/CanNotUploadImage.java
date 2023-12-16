package com.project.supermarket_be.api.exception.customerException;

import java.text.MessageFormat;

public class CanNotUploadImage extends BaseException{
    private static final String MESSAGE_KEY = "cannot.upload.base64";
    public CanNotUploadImage(String base64img) {
        super(MessageFormat.format(getMessage(MESSAGE_KEY), base64img));
    }
}
