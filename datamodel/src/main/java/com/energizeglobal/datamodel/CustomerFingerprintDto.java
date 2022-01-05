package com.energizeglobal.datamodel;

import com.energizeglobal.infrastructure.AbstractValueObject;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class CustomerFingerprintDto extends AbstractValueObject {
    private CustomerDto customerDto;
    private String fingerprint;

    public CustomerFingerprintDto() {
        this.customerDto = new CustomerDto();
    }

    public CustomerFingerprintDto(Long id, CustomerDto customerDto, String fingerprint, LocalDateTime insertDate, LocalDateTime modifyDate, Integer version) {
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

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("customerDto='" + customerDto.toString() + "'")
                .add("fingerprint='" + fingerprint + "'")
                .add("insertDate='" + insertDate + "'")
                .add("modifyDate='" + modifyDate + "'")
                .add("version='" + version + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerFingerprintDto that = (CustomerFingerprintDto) o;
        return Objects.equals(customerDto, that.customerDto) && Objects.equals(fingerprint, that.fingerprint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerDto, fingerprint);
    }
}
