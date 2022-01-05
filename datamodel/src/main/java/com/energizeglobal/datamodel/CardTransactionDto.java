package com.energizeglobal.datamodel;


import com.energizeglobal.datamodel.types.TransactionType;
import com.energizeglobal.infrastructure.AbstractValueObject;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class CardTransactionDto extends AbstractValueObject {
    private CustomerCardDto customerCardDto;
    private AtmMachineDto atmMachineDto;
    private TransactionType transactionType;
    public BigDecimal transactionAmount;

    public CardTransactionDto() {
        this.customerCardDto = new CustomerCardDto();
        this.atmMachineDto = new AtmMachineDto();
    }

    public CardTransactionDto(Long id, CustomerCardDto customerCardDto, AtmMachineDto atmMachineDto, TransactionType transactionType, BigDecimal transactionAmount, LocalDateTime insertDate, LocalDateTime modifyDate, Integer version) {
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

    public Long getCardNumber() {
        return this.customerCardDto.getCardNumber();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("customerCardDto='" + customerCardDto.toString() + "'")
                .add("atmMachineDto='" + atmMachineDto.toString() + "'")
                .add("transactionType='" + transactionType.toString() + "'")
                .add("transactionAmount='" + transactionAmount + "'")
                .add("insertDate='" + insertDate + "'")
                .add("modifyDate='" + modifyDate + "'")
                .add("version='" + version + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardTransactionDto that = (CardTransactionDto) o;
        return Objects.equals(customerCardDto, that.customerCardDto) && Objects.equals(atmMachineDto, that.atmMachineDto) &&
                transactionType == that.transactionType && Objects.equals(transactionAmount, that.transactionAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerCardDto, atmMachineDto, transactionType, transactionAmount);
    }
}

