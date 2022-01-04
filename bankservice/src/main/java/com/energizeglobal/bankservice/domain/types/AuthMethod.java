package com.energizeglobal.bankservice.domain.types;

import com.energizeglobal.bankservice.infrastructure.AbstractEnumConverter;
import com.energizeglobal.bankservice.infrastructure.Convertible;

public enum AuthMethod implements Convertible<String>{
    PIN ("P"),
    FINGERPRINT("F");

    private final String value;

    @Override
    public String getValue() {
        return this.value;
    }

    AuthMethod(String value) {
        this.value = value;
    }

    public static AuthMethod getByUniqueId(String value) {
        if (value == null) return null;
        for (AuthMethod authMethod : values()) {
            if (authMethod.value.equals(value))
                return authMethod;
        }
        throw new IllegalArgumentException("AuthMethod not found");
    }

    public static class AuthMethodConverter extends AbstractEnumConverter<String , AuthMethod> {
    }
}
