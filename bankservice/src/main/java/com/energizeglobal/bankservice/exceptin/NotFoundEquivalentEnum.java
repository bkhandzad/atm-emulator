package com.energizeglobal.bankservice.exceptin;


import com.energizeglobal.bankservice.infrastructure.AbstractException;
import org.springframework.http.HttpStatus;

/**
 * When not found equivalent enum for persist value, throw this exception.
 *
 */

public class NotFoundEquivalentEnum extends AbstractException {

    public NotFoundEquivalentEnum(Object... args) {
        super("error.orm.convertColumnToEnum");
        setCode("error.orm.convertColumnToEnum.code");
        setSuggestion("error.orm.convertColumnToEnum.suggestion");
        setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE);
        setArgs(args);
    }
}
