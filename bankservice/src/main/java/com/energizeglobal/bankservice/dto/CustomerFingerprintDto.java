package com.energizeglobal.bankservice.dto;

import com.energizeglobal.infrastructure.AbstractValueObject;

import java.time.LocalDateTime;

public class CustomerFingerprintDto extends AbstractValueObject {
    private CustomerDto customerDto;
    private String fingerprint;

    public CustomerFingerprintDto() {
    }

    public CustomerFingerprintDto(Long id, CustomerDto customerDto, String fingerprint, LocalDateTime insertDate, LocalDateTime modifyDate,Integer version) {
        this.id = id;
        this.customerDto = customerDto;
        this.fingerprint = fingerprint;
        this.insertDate = insertDate;
        this.modifyDate = modifyDate;
        this.version = version;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }
}
