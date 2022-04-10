package com.example.cookie.oauth.domain;

import lombok.Data;

import java.util.Map;

@Data
public class KakaoProfile {

    private String id;
    private String connected_at;
    private Map<String, Object> properties;
    private Map<String, Object> kakao_account;
}