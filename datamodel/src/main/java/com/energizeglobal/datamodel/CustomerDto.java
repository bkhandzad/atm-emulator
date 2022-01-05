package com.energizeglobal.datamodel;

import com.energizeglobal.datamodel.types.CustomerType;
import com.energizeglobal.infrastructure.AbstractValueObject;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("nationalId='" + nationalId + "'")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("customerType='" + customerType.toString() + "'")
                .add("insertDate='" + insertDate + "'")
                .add("modifyDate='" + modifyDate + "'")
                .add("version='" + version + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(nationalId, that.nationalId) && Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) && customerType == that.customerType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalId, firstName, lastName, customerType);
    }
}