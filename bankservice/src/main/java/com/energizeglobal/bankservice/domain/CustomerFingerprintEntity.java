package com.energizeglobal.bankservice.domain;

import com.energizeglobal.bankservice.infrastructure.AbstractPersistence;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "BANK_CUSTOMER_FINGERPRINT", uniqueConstraints = @UniqueConstraint(name = "BANK_CUSTOMER_FINGERPRINT_UQ", columnNames = {"FINGERPRINT"}))
public class CustomerFingerprintEntity extends AbstractPersistence {

    private CustomerEntity customerEntity;

    private String fingerprint;

    @Id
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "NUMBER(17)")
    @SequenceGenerator(name = "BANK_CUSTOMER_FINGERPRINT_GEN", sequenceName = "BANK_CUSTOMER_FINGERPRINT_SEQ", allocationSize = ALLOCATION_SIZE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_CUSTOMER_FINGERPRINT_GEN")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "FINGERPRINT", nullable = false, length = 256, columnDefinition = "VARCHAR2(256)")
    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BANK_CUSTOMER_ID", foreignKey = @ForeignKey(name = "CUSTOMER_FINGERPRINT_FK_CUSTOMER"), referencedColumnName = "ID", nullable = false)
    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
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
        CustomerFingerprintEntity customerFingerprintEntity = (CustomerFingerprintEntity) o;
        return Objects.equals(id, customerFingerprintEntity.id) &&
                customerEntity.equals(customerFingerprintEntity.customerEntity) &&
                Objects.equals(fingerprint, customerFingerprintEntity.fingerprint)&&
                Objects.equals(version, customerFingerprintEntity.version)&&
                Objects.equals(insertDate, customerFingerprintEntity.insertDate)&&
                Objects.equals(modifyDate, customerFingerprintEntity.modifyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerEntity.getId(), fingerprint);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("customer='" + customerEntity.getId() + "'")
                .add("fingerprint='********'")
                .toString();
    }
}
