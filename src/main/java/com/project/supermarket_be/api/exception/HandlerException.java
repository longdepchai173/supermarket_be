package com.project.supermarket_be.api.exception;


import com.project.supermarket_be.api.exception.customerException.*;
import com.project.supermarket_be.api.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }


//    @ExceptionHandler(InvalidArgumentException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Map<String, String> handlerInvalidArgumentException(InvalidArgumentException ex){
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//            errors.put(error.getField(), error.getDefaultMessage());
//        });
//        return errors;
//    }

//    @ExceptionHandler(DisabledException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ErrorResponse handlerDisabledException(DisabledException ex){
//        return new ErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
//    }


    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerUserNotFoundException(UserNotFoundException ex){
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ShelfCodeAlreadyExists.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleShelfCodeAlreadyExists(ShelfCodeAlreadyExists ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    @ExceptionHandler(ProductCannotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerProductNotFoundException(ProductCannotFound ex){
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CanNotUploadImage.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerCannotUploadBase64(CanNotUploadImage ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InventoryCannotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerCannotFoundInventory(InventoryCannotFound ex){
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CannotCreateProduct.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerCannotCreateProduct(CannotCreateProduct ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
//    @ExceptionHandler(BadCredentialsException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handlerBadCredentialsException(BadCredentialsException ex){
//        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
//    }

    @ExceptionHandler(PasswordErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerPasswordError(PasswordErrorException ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CompartmentHasDiffProduct.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCompHasDifPro(CompartmentHasDiffProduct ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
    @ExceptionHandler(EmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEmptyException(EmptyException ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ErrorResponse handlerAccessDeniedException(AccessDeniedException ex){
//        return new ErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage());
//    }
//    @ExceptionHandler(LockedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ErrorResponse handlerLockedException(LockedException ex){
//        return new ErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
//    }
//    @ExceptionHandler(AccountExpiredException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ErrorResponse handlerAccountExpired(AccessDeniedException ex){
//        return new ErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
//    }
//    @ExceptionHandler(CredentialsExpiredException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ErrorResponse handlerCredentialsExpiredException(CredentialsExpiredException ex){
//        return new ErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
//    }

    @ExceptionHandler(CannotCreateUser.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerCannotCreateUser(CannotCreateUser ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(UserIDNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerUserIDNotFoundException(UserIDNotFoundException ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CannotFoundWarehouseInvoice.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerWarehouseInvoice(CannotFoundWarehouseInvoice ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(TokenIsEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorResponse handleTokenIsEmpty(TokenIsEmptyException ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(NotHavePermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleUserHaveNotPermission(NotHavePermissionException ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(CanNotFoundProvider.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNotFoundProvider(CanNotFoundProvider ex){
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

}
