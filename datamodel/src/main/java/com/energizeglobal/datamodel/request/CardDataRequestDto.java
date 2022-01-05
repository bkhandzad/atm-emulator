package com.energizeglobal.datamodel.request;

import com.energizeglobal.datamodel.types.CardAuthResult;

import java.util.Objects;
import java.util.StringJoiner;

public class CardDataRequestDto {
    private Long id;
    private String cardAuthenticationValue;
    private CardAuthResult error;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("cardAuthenticationValue='" + cardAuthenticationValue + "'")
                .add("error='" + error + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDataRequestDto that = (CardDataRequestDto) o;
        return Objects.equals(id, that.id) && Objects.equals(cardAuthenticationValue, that.cardAuthenticationValue) && error == that.error;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardAuthenticationValue, error);
    }
}
