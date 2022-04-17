package com.example.cookie.security.oauth.domain;

import lombok.Data;

import java.util.Map;

@Data
public class NaverProfile {

    private String resultcode;
    private String message;
    private Map<String, Object> response;
}