package com.energizeglobal.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * All of exceptions must extend AbstractException.
 * All of exceptions are runtime exception and will handle with centralized exception handling mechanism.
 *
 */
public abstract class AbstractException extends RuntimeException {
    private Logger logger = LoggerFactory.getLogger(Logger.class);

    private String code;

    private String suggestion;

    private Object[] args;

    private HttpStatus httpStatus;

    private String localMessage;

    private String localCode;

    private String localSuggestion;

    private List<AbstractException> children = new ArrayList<>();

    public AbstractException(String message) {
        super(message);
        init();
        logger.error(message);
    }

    public void init() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getLocalMessage() {
        return localMessage;
    }

    public void setLocalMessage(String message) {
        this.localMessage = message;
    }

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public String getLocalSuggestion() {
        return localSuggestion;
    }

    public void setLocalSuggestion(String localSuggestion) {
        this.localSuggestion = localSuggestion;
    }

    public <T extends AbstractException> T addChild(T e) {
        children.add(e);
        return (T) this;
    }

    public List<AbstractException> getChildren() {
        return children;
    }

    public void setChildren(List<AbstractException> children) {
        this.children = children;
    }
}
