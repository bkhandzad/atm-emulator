package com.energizeglobal.datamodel;

import com.energizeglobal.datamodel.types.AuthMethod;
import com.energizeglobal.datamodel.types.CardAuthResult;
import com.energizeglobal.datamodel.types.CardState;
import com.energizeglobal.infrastructure.AbstractValueObject;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class CustomerCardDto extends AbstractValueObject {
    private CardState cardState;
    private CustomerDto customerDto;
    private Long cardNumber;
    private Long accountNumber;
    private String cvv2;
    private LocalDateTime issueDate;
    private LocalDateTime expireDate;
    private Integer incorrectPIN;
    private AuthMethod authMethod;
    private String cardAuthenticationValue;
    private CardAuthResult error;

    public CustomerCardDto() {
    }

    public CustomerCardDto(Long id, CardState cardState, CustomerDto customerDto, Long cardNumber, Long accountNumber, String cvv2, LocalDateTime issueDate, LocalDateTime expireDate, Integer incorrectPIN, LocalDateTime insertDate, LocalDateTime modifyDate, Integer version) {
        this.id = id;
        this.cardState = cardState;
        this.customerDto = customerDto;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.cvv2 = cvv2;
        this.issueDate = issueDate;
        this.expireDate = expireDate;
        this.incorrectPIN = incorrectPIN;
        this.insertDate = insertDate;
        this.modifyDate = modifyDate;
        this.version = version;
    }

    public CardState getCardState() {
        return cardState;
    }

    public void setCardState(CardState cardState) {
        this.cardState = cardState;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getIncorrectPIN() {
        return incorrectPIN;
    }

    public void setIncorrectPIN(Integer incorrectPIN) {
        this.incorrectPIN = incorrectPIN;
    }

    public AuthMethod getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(AuthMethod authMethod) {
        this.authMethod = authMethod;
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
                .add("cardState='" + cardState + "'")
                .add("customerDto='" + customerDto.toString() + "'")
                .add("cardNumber='" + cardNumber + "'")
                .add("accountNumber='" + accountNumber + "'")
                .add("cvv2='" + cvv2 + "'")
                .add("issueDate='" + issueDate + "'")
                .add("expireDate='" + expireDate + "'")
                .add("incorrectPIN='" + incorrectPIN + "'")
                .add("insertDate='" + insertDate + "'")
                .add("modifyDate='" + modifyDate + "'")
                .add("version='" + version + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerCardDto that = (CustomerCardDto) o;
        return cardState == that.cardState && Objects.equals(customerDto, that.customerDto) &&
                Objects.equals(cardNumber, that.cardNumber) && Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(cvv2, that.cvv2) && Objects.equals(issueDate, that.issueDate) && Objects.equals(expireDate, that.expireDate) &&
                Objects.equals(incorrectPIN, that.incorrectPIN) && authMethod == that.authMethod &&
                Objects.equals(cardAuthenticationValue, that.cardAuthenticationValue) && error == that.error;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardState, customerDto, cardNumber, accountNumber, cvv2, issueDate, expireDate, incorrectPIN, authMethod, cardAuthenticationValue, error);
    }


}
