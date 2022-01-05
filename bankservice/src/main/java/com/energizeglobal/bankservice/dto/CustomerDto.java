package com.energizeglobal.bankservice.dto;

import com.energizeglobal.bankservice.domain.types.CustomerType;
import com.energizeglobal.infrastructure.AbstractValueObject;

import java.time.LocalDateTime;

public class CustomerDto extends AbstractValueObject {
    public Long nationalId;
    private String firstName;
    private String lastName;
    private CustomerType customerType;

    public CustomerDto() {
    }

    public CustomerDto(Long id, Long nationalId, String firstName, String lastName, CustomerType customerType, LocalDateTime insertDate, LocalDateTime modifyDate,Integer version) {
        this.id = id;
        this.nationalId = nationalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerType = customerType;
        this.insertDate = insertDate;
        this.modifyDate = modifyDate;
        this.version = version;
    }

    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
