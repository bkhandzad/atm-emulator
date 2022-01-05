package com.energizeglobal.bankservice.dto;


import com.energizeglobal.bankservice.domain.types.TransactionType;
import com.energizeglobal.infrastructure.AbstractValueObject;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CardTransactionDto extends AbstractValueObject {
    private CustomerCardDto customerCardDto;
    private AtmMachineDto atmMachineDto;
    private TransactionType transactionType;
    public BigDecimal transactionAmount;

    public CardTransactionDto() {
    }

    public CardTransactionDto(Long id, CustomerCardDto customerCardDto, AtmMachineDto atmMachineDto, TransactionType transactionType, BigDecimal transactionAmount, LocalDateTime insertDate, LocalDateTime modifyDate,Integer version) {
        this.id = id;
        this.customerCardDto = customerCardDto;
        this.atmMachineDto = atmMachineDto;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.insertDate = insertDate;
        this.modifyDate = modifyDate;
        this.version = version;
    }

    public CustomerCardDto getCustomerCardDto() {
        return customerCardDto;
    }

    public void setCustomerCardDto(CustomerCardDto customerCardDto) {
        this.customerCardDto = customerCardDto;
    }

    public AtmMachineDto getAtmMachineDto() {
        return atmMachineDto;
    }

    public void setAtmMachineDto(AtmMachineDto atmMachineDto) {
        this.atmMachineDto = atmMachineDto;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
