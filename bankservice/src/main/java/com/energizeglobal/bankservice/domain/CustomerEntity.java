package com.energizeglobal.bankservice.domain;

import com.energizeglobal.bankservice.domain.types.CustomerType;
import com.energizeglobal.infrastructure.AbstractPersistence;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "BANK_CUSTOMER", uniqueConstraints = @UniqueConstraint(name = "BANK_CUSTOMER_NATIONAL_CODE_UQ", columnNames = {"NATIONAL_ID"}))
public class CustomerEntity extends AbstractPersistence {

    public Long nationalId;

    private String firstName;

    private String lastName;

    private CustomerType customerType;

    @Id
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "NUMBER(17)")
    @SequenceGenerator(name = "BANK_CUSTOMER_GEN", sequenceName = "BANK_CUSTOMER_SEQ", allocationSize = ALLOCATION_SIZE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_CUSTOMER_GEN")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NATIONAL_ID", nullable = false, precision = 17, columnDefinition = "NUMBER(17)")
    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
    }

    @Column(name = "FIRST_NAME", length = 50, columnDefinition = "VARCHAR2(50)")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME", nullable = false, length = 250, columnDefinition = "VARCHAR2(250)")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "CUSTOMER_TYPE", nullable = false, columnDefinition = "VARCHAR2(4)")
    @Convert(converter = CustomerType.CustomerTypeConverter.class)
    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Column(name = "INSERT_DATE", updatable = false)
    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    @Column(name = "MODIFY_DATE")
    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Column(name = "VERSION")
    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity customerEntity = (CustomerEntity) o;
        return Objects.equals(id, customerEntity.id) &&
                Objects.equals(firstName, customerEntity.firstName) &&
                Objects.equals(lastName, customerEntity.lastName) &&
                Objects.equals(customerType, customerEntity.customerType) &&
                Objects.equals(nationalId, customerEntity.nationalId)&&
                Objects.equals(version, customerEntity.version)&&
                Objects.equals(insertDate, customerEntity.insertDate)&&
                Objects.equals(modifyDate, customerEntity.modifyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, customerType, nationalId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("nationalId='" + nationalId + "'")
                .add("customerType='" + customerType + "'")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .toString();
    }
}
