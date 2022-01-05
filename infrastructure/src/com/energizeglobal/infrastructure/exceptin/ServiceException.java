package com.energizeglobal.infrastructure.exceptin;

import com.energizeglobal.infrastructure.AbstractException;
import org.springframework.http.HttpStatus;

public class ServiceException extends AbstractException {
    public ServiceException(String message) {
        super(String.format("Illegal Object : %s",message));
        setHttpStatus(HttpStatus.NOT_FOUND);
    }
}
