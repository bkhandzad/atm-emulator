package com.energizeglobal.datamodel.types;

import com.energizeglobal.infrastructure.AbstractEnumConverter;
import com.energizeglobal.infrastructure.Convertible;

public enum CardState implements Convertible<String> {
    READY("R"), // Need to change Pin
    USABLE("U"), // Customer is allow to use card
    BLOCK("B"); // Card Has been blocked

    private final String value;

    @Override
    public String getValue() {
        return this.value;
    }

    CardState(String value) {
        this.value = value;
    }

    public static CardState getByUniqueId(String value) {
        if (value == null) return null;
        for (CardState cardState : values()) {
            if (cardState.value.equals(value))
                return cardState;
        }
        throw new IllegalArgumentException("CardState not found");
    }

    public static class CardStateConverter extends AbstractEnumConverter<String , CardState> {
    }

}
