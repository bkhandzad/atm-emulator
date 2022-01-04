package com.energizeglobal.datamodel;

import com.energizeglobal.datamodel.type.CardAuthResult;

public class CustomerCard {
    private Long id;
    private Long cardNumber;
    private String cardAuthentication;
    private CardAuthResult error;

    public CustomerCard() {
    }

    public CustomerCard(Long id, Long cardNumber, String cardAuthentication) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardAuthentication = cardAuthentication;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardAuthentication() {
        return cardAuthentication;
    }

    public void setCardAuthentication(String cardAuthentication) {
        this.cardAuthentication = cardAuthentication;
    }

    public CardAuthResult getError() {
        return error;
    }

    public void setError(CardAuthResult error) {
        this.error = error;
    }
}
