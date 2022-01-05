package com.energizeglobal.bankservice.domain;

import com.energizeglobal.datamodel.types.AuthMethod;
import com.energizeglobal.datamodel.types.CardState;
import com.energizeglobal.infrastructure.AbstractPersistence;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "BANK_CUSTOMER_CARD", uniqueConstraints = @UniqueConstraint(name = "BANK_CUSTOMER_CARD_UQ", columnNames = {"CARD_NUMBER","EXPIRE_DATE","CVV2"}))
public class CustomerCardEntity extends AbstractPersistence {

    private CardState cardState;

    private CustomerEntity customerEntity;

    private Long cardNumber;

    private Long accountNumber;

    private Long cardPIN;

    private String cvv2;

    private LocalDateTime issueDate;

    private LocalDateTime expireDate;

    private Integer incorrectPIN;

    private AuthMethod authMethod;

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

    @Column(name = "ACCOUNT_NUMBER", nullable = false, columnDefinition = "NUMBER(16)")
    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long cardNumber) {
        this.accountNumber = cardNumber;
    }

    @Column(name = "CARD_NUMBER", nullable = false, columnDefinition = "NUMBER(16)")
    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "BANK_CUSTOMER_ID", foreignKey = @ForeignKey(name = "CUSTOMER_CARD_FK_CUSTOMER"), referencedColumnName = "ID", nullable = false)
    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    @Column(name = "CVV2", nullable = false, length = 4, columnDefinition = "VARCHAR2(4)")
    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    @Column(name = "ISSUE_DATE", updatable = false)
    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    @Column(name = "EXPIRE_DATE", updatable = false)
    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    @Column(name = "CARD_STATE", nullable = false, columnDefinition = "VARCHAR2(4)")
    @Convert(converter = CardState.CardStateConverter.class)
    public CardState getCardState() {
        return cardState;
    }

    public void setCardState(CardState cardState) {
        this.cardState = cardState;
    }

    @Column(name = "CARD_PIN", nullable = false, columnDefinition = "NUMBER(4)")
    public Long getCardPIN() {
        return cardPIN;
    }

    public void setCardPIN(Long cardPIN) {
        this.cardPIN = cardPIN;
    }

    @Column(name = "INCORRECT_PIN", columnDefinition = "NUMBER(4) default 0")
    public Integer getIncorrectPIN() {
        return incorrectPIN;
    }

    public void setIncorrectPIN(Integer incorrectPIN) {
        this.incorrectPIN = incorrectPIN;
    }

    @Column(name = "AUTH_METHOD", nullable = false, columnDefinition = "VARCHAR2(4)")
    @Convert(converter = AuthMethod.AuthMethodConverter.class)
    public AuthMethod getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(AuthMethod authMethod) {
        this.authMethod = authMethod;
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
        CustomerCardEntity customerCardEntity = (CustomerCardEntity) o;
        return Objects.equals(id, customerCardEntity.id) &&
                customerEntity.equals(customerCardEntity.customerEntity) &&
                Objects.equals(cardNumber, customerCardEntity.cardNumber) &&
                Objects.equals(cardState, customerCardEntity.cardState) &&
                Objects.equals(cvv2, customerCardEntity.cvv2) &&
                Objects.equals(issueDate, customerCardEntity.issueDate) &&
                Objects.equals(expireDate, customerCardEntity.expireDate)&&
                Objects.equals(version, customerCardEntity.version)&&
                Objects.equals(insertDate, customerCardEntity.insertDate)&&
                Objects.equals(modifyDate, customerCardEntity.modifyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, cvv2, issueDate, expireDate);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("customer='" + customerEntity.getId() + "'")
                .add("cardNumber='" + cardNumber + "'")
                .add("cvv2='" + cvv2 + "'")
                .add("issueDate='" + issueDate + "'")
                .add("expireDate='" + expireDate + "'")
                .add("cardState='" + cardState + "'")
                .add("cardPIN='****'")
                .toString();
    }
}
