package com.energizeglobal.datamodel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class Transaction {
    private Long id;
    private BigDecimal amount;
    private Long cardNumber;
    private LocalDateTime transactionTime;
    private String transactionType;
    private AtmMachine atmMachine;

    public Transaction() {
    }

    public Transaction(Long id, BigDecimal amount, Long cardNumber, LocalDateTime transactionTime, String transactionType, AtmMachine atmMachine) {
        this.id = id;
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.transactionTime = transactionTime;
        this.transactionType = transactionType;
        this.atmMachine = atmMachine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public AtmMachine getAtmMachine() {
        return atmMachine;
    }

    public void setAtmMachine(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("amount='" + amount + "'")
                .add("cardNumber='" + cardNumber + "'")
                .add("transactionTime='" + transactionTime + "'")
                .add("transactionType='" + transactionType + "'")
                .add("atmMachine='" + atmMachine + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction result = (Transaction) o;
        return Objects.equals(id, result.id) && Objects.equals(amount, result.amount) && Objects.equals(cardNumber, result.cardNumber)
                && Objects.equals(transactionTime, result.transactionTime) && Objects.equals(transactionType, result.transactionType)&& Objects.equals(atmMachine, result.atmMachine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, cardNumber, transactionTime, transactionType, atmMachine);
    }
}
