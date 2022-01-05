package com.energizeglobal.datamodel.request;

import com.energizeglobal.datamodel.types.CardTransactionResult;

import java.util.Objects;
import java.util.StringJoiner;

public class TransactionsRequestDto {
    private Long cardNumber;
    private Integer count;

    public TransactionsRequestDto(Long cardNumber, Integer count) {
        this.cardNumber = cardNumber;
        this.count = count;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionsRequestDto that = (TransactionsRequestDto) o;
        return Objects.equals(cardNumber, that.cardNumber) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, count);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("cardNumber='" + cardNumber + "'")
                .add("count='" + count + "'")
                .toString();
    }
}
