package com.energizeglobal.datamodel;

import java.util.Objects;
import java.util.StringJoiner;

public class AtmMachine {
    private Long id;

    private String username;

    private String password;

    public AtmMachine() {
    }

    public AtmMachine(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public AtmMachine(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("id='" + id + "'")
                .add("username='" + username + "'")
                .add("password='*****'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtmMachine result = (AtmMachine) o;
        return Objects.equals(id, result.id) && Objects.equals(username, result.username) && Objects.equals(password, result.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
