package com.energizeglobal.datamodel;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class CardBalanceVo {
    private BigDecimal balance;
    private Long cardNumber;

    public CardBalanceVo() {
    }

    public CardBalanceVo(BigDecimal balance, Long cardNumber) {
        this.balance = balance;
        this.cardNumber = cardNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("balance='" + balance + "'")
                .add("cardNumber='" + cardNumber + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardBalanceVo result = (CardBalanceVo) o;
        return Objects.equals(balance, result.balance)  && Objects.equals(cardNumber, result.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, cardNumber);
    }
}
