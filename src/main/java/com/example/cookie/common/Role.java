package com.example.cookie.common;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

    private String key;

    public String getRokeKey() {
        return this.key;
    }
}
