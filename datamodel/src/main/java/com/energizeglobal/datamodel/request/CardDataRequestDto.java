package com.energizeglobal.datamodel.request;

import com.energizeglobal.datamodel.types.AuthMethod;
import com.energizeglobal.datamodel.types.CardAuthResult;
import com.energizeglobal.datamodel.types.CardState;

import java.util.Objects;
import java.util.StringJoiner;

public class CardDataRequestDto {
    private Long id;
    private Long cardNumber;
    private String cardAuthenticationValue;
    private CardAuthResult error;
    private AuthMethod authMethod;
    private CardState cardState;

    public CardDataRequestDto() {
    }

    public CardDataRequestDto(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardDataRequestDto(Long cardNumber, String cardAuthenticationValue) {
        this.cardNumber = cardNumber;
        this.cardAuthenticationValue = cardAuthenticationValue;
    }

    public CardDataRequestDto(Long id, Long cardNumber, String cardAuthenticationValue, CardAuthResult error, AuthMethod authMethod, CardState cardState) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardAuthenticationValue = cardAuthenticationValue;
        this.error = error;
        this.authMethod = authMethod;
        this.cardState = cardState;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardAuthenticationValue() {
        return cardAuthenticationValue;
    }

    public void setCardAuthenticationValue(String cardAuthenticationValue) {
        this.cardAuthenticationValue = cardAuthenticationValue;
    }

    public CardAuthResult getError() {
        return error;
    }

    public void setError(CardAuthResult error) {
        this.error = error;
    }

    public AuthMethod getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(AuthMethod authMethod) {
        this.authMethod = authMethod;
    }

    public CardState getCardState() {
        return cardState;
    }

    public void setCardState(CardState cardState) {
        this.cardState = cardState;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("cardNumber='" + cardNumber + "'")
                .add("cardAuthenticationValue='" + cardAuthenticationValue + "'")
                .add("error='" + error + "'")
                .add("cardState='" + cardState.toString() + "'")
                .add("authMethod='" + authMethod.toString() + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDataRequestDto that = (CardDataRequestDto) o;
        return id.equals(that.id) && cardNumber.equals(that.cardNumber) && cardAuthenticationValue.equals(that.cardAuthenticationValue) && error == that.error && authMethod == that.authMethod && cardState == that.cardState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, cardAuthenticationValue, error, authMethod, cardState);
    }
}
