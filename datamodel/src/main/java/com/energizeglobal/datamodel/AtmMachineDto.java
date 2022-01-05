package com.energizeglobal.datamodel;

import com.energizeglobal.infrastructure.AbstractValueObject;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

public class AtmMachineDto extends AbstractValueObject {
    private String userName;
    private String passWord;

    public AtmMachineDto() {
    }

    public AtmMachineDto(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public AtmMachineDto(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public AtmMachineDto(Long id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    public AtmMachineDto(Long id, String userName, String passWord, LocalDateTime insertDate, LocalDateTime modifyDate,Integer version) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.insertDate = insertDate;
        this.modifyDate = modifyDate;
        this.version = version;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("username='" + userName + "'")
                .add("password='*****'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtmMachineDto result = (AtmMachineDto) o;
        return Objects.equals(id, result.id) && Objects.equals(userName, result.userName) && Objects.equals(passWord, result.passWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, passWord);
    }
}
