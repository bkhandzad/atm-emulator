package com.energizeglobal.bankservice.exceptin;

import com.energizeglobal.bankservice.infrastructure.AbstractException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class ServiceException extends AbstractException {
    public ServiceException(String message) {
        super(String.format("Illegal Object : %s",message));
        setHttpStatus(HttpStatus.NOT_FOUND);
    }
}
