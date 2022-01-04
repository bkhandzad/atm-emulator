package com.energizeglobal.bankservice.domain;

import com.energizeglobal.bankservice.infrastructure.AbstractPersistence;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "BANK_ATM_MACHINE", uniqueConstraints = @UniqueConstraint(name = "BANK_ATM_MACHINE_AUTH_UQ", columnNames = {"USER_NAME"}))
public class AtmMachineEntity extends AbstractPersistence {

    private String username;

    private String password;

    @Id
    @Column(name = "ID", unique = true, nullable = false, columnDefinition = "NUMBER(17)")
    @SequenceGenerator(name = "BANK_ATM_MACHINE_GEN", sequenceName = "BANK_ATM_MACHINE_SEQ", allocationSize = ALLOCATION_SIZE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_ATM_MACHINE_GEN")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "USER_NAME", nullable = false, length = 50, columnDefinition = "VARCHAR2(50)")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD", nullable = false, length = 250, columnDefinition = "VARCHAR2(250)")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        AtmMachineEntity atmMachineEntity = (AtmMachineEntity) o;
        return Objects.equals(id, atmMachineEntity.id) &&
                Objects.equals(username, atmMachineEntity.username) &&
                Objects.equals(password, atmMachineEntity.password)&&
                Objects.equals(version, atmMachineEntity.version)&&
                Objects.equals(insertDate, atmMachineEntity.insertDate)&&
                Objects.equals(modifyDate, atmMachineEntity.modifyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("userName='" + username + "'")
                .add("passWord='********'")
                .toString();
    }
}
