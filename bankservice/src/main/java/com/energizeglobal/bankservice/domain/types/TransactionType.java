package com.energizeglobal.bankservice.domain.types;

import com.energizeglobal.infrastructure.AbstractEnumConverter;
import com.energizeglobal.infrastructure.Convertible;

public enum TransactionType implements Convertible<Integer> {
    WITHDRAW(-1),
    DEPOSIT(1);

    private final Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    TransactionType(Integer value) {
        this.value = value;
    }

    public static TransactionType getByUniqueId(String value) {
        if (value == null) return null;
        for (TransactionType transactionType : values()) {
            if (transactionType.value.equals(value))
                return transactionType;
        }
        throw new IllegalArgumentException("TransactionType not found");
    }

    public static class TransactionTypeConverter extends AbstractEnumConverter<Integer , TransactionType> {
    }
}
