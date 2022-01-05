package com.energizeglobal.bankservice.dto;

import com.energizeglobal.infrastructure.AbstractValueObject;

import java.time.LocalDateTime;

public class AtmMachineDto extends AbstractValueObject {
    private String userName;
    private String passWord;

    public AtmMachineDto() {
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
}
