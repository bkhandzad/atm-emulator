package com.energizeglobal.bankservice.domain.types;

import com.energizeglobal.infrastructure.AbstractEnumConverter;
import com.energizeglobal.infrastructure.Convertible;

public enum CustomerType implements Convertible<String> {
    REAL("R"),
    LEGAL("L");

    private final String value;

    @Override
    public String getValue() {
        return this.value;
    }

    CustomerType(String value) {
        this.value = value;
    }

    public static CustomerType getByUniqueId(String value) {
        if (value == null) return null;
        for (CustomerType customerType : values()) {
            if (customerType.value.equals(value))
                return customerType;
        }
        throw new IllegalArgumentException("CustomerType not found");
    }

    public static class CustomerTypeConverter extends AbstractEnumConverter<String , CustomerType> {
    }
}
