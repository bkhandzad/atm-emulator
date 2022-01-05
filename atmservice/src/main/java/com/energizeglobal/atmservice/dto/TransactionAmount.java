package com.energizeglobal.atmservice.dto;

import java.math.BigDecimal;

public class TransactionAmount {
    private BigDecimal amount;

    public TransactionAmount() {
    }

    public TransactionAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionAmount{" +
                "amount=" + amount +
                '}';
    }
}
