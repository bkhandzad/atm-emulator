package com.energizeglobal.bankservice.domain;

import com.energizeglobal.bankservice.domain.types.TransactionType;
import com.energizeglobal.infrastructure.AbstractPersistence;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "BANK_CUSTOMER_CARD_TRANSACTION")
public class CardTransactionEntity extends AbstractPersistence {

    private CustomerCardEntity customerCardEntity;

    private AtmMachineEntity atmMachineEntity;

    private TransactionType transactionType;

    public BigDecimal transactionAmount;

    @Id
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "NUMBER(17)")
    @SequenceGenerator(name = "BANK_CUSTOMER_CARD_GEN", sequenceName = "BANK_CUSTOMER_CARD_SEQ", allocationSize = ALLOCATION_SIZE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_CUSTOMER_CARD_GEN")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "CUSTOMER_CARD_ID", foreignKey = @ForeignKey(name = "TRANSACTION_FK_CUSTOMER_CARD"), referencedColumnName = "ID", nullable = false)
    public CustomerCardEntity getCustomerCardEntity() {
        return customerCardEntity;
    }

    public void setCustomerCardEntity(CustomerCardEntity customerCardEntity) {
        this.customerCardEntity = customerCardEntity;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "ATM_ID", foreignKey = @ForeignKey(name = "TRANSACTION_FK_ATM"), referencedColumnName = "ID", nullable = false)
    public AtmMachineEntity getAtmMachineEntity() {
        return atmMachineEntity;
    }

    public void setAtmMachineEntity(AtmMachineEntity atmMachineEntity) {
        this.atmMachineEntity = atmMachineEntity;
    }

    @Column(name = "TRANSACTION_TYPE", nullable = false, columnDefinition = "NUMBER")
    @Convert(converter = TransactionType.TransactionTypeConverter.class)
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Column(name = "TRANSACTION_AMOUNT", precision = 24, scale = 4, nullable = false)
    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
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
        CardTransactionEntity cardTransactionEntity = (CardTransactionEntity) o;
        return Objects.equals(id, cardTransactionEntity.id) &&
                customerCardEntity.equals(cardTransactionEntity.customerCardEntity) &&
                atmMachineEntity.equals(cardTransactionEntity.atmMachineEntity) &&
                Objects.equals(transactionType, cardTransactionEntity.transactionType) &&
                Objects.equals(transactionAmount, cardTransactionEntity.transactionAmount)&&
                Objects.equals(version, cardTransactionEntity.version)&&
                Objects.equals(insertDate, cardTransactionEntity.insertDate)&&
                Objects.equals(modifyDate, cardTransactionEntity.modifyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerCardEntity, atmMachineEntity, transactionType, transactionAmount);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("customerCardEntity='" + customerCardEntity.getId() + "'")
                .add("atmMachineEntity='" + atmMachineEntity.getId() + "'")
                .add("transactionType='" + transactionType.getValue() + "'")
                .add("transactionAmount='" + transactionAmount + "'")
                .toString();
    }
}
