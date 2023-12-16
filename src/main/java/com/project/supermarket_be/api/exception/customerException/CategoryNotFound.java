package com.project.supermarket_be.api.exception.customerException;

public class CategoryNotFound extends BaseException{
    private static final String MESSAGE_KEY = "category.not.found";
    public CategoryNotFound(String message) {
        super(message);
    }
}
