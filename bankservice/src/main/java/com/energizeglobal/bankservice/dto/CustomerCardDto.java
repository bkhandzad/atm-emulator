package com.energizeglobal.bankservice.dto;

import com.energizeglobal.bankservice.domain.types.AuthMethod;
import com.energizeglobal.bankservice.domain.types.CardState;
import com.energizeglobal.infrastructure.AbstractValueObject;

import java.time.LocalDateTime;

public class CustomerCardDto extends AbstractValueObject {
    private CardState cardState;
    private CustomerDto customerDto;
    private Long cardNumber;
    private Long accountNumber;
    private Long cardPIN;
    private String cvv2;
    private LocalDateTime issueDate;
    private LocalDateTime expireDate;
    private Integer incorrectPIN;
    private AuthMethod authMethod;

    public CustomerCardDto() {
    }

    public CustomerCardDto(Long id, CardState cardState, CustomerDto customerDto, Long cardNumber, Long accountNumber, Long cardPIN, String cvv2, LocalDateTime issueDate, LocalDateTime expireDate, Integer incorrectPIN, LocalDateTime insertDate, LocalDateTime modifyDate,Integer version) {
        this.id = id;
        this.cardState = cardState;
        this.customerDto = customerDto;
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.cardPIN = cardPIN;
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

    public Long getCardPIN() {
        return cardPIN;
    }

    public void setCardPIN(Long cardPIN) {
        this.cardPIN = cardPIN;
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
}
